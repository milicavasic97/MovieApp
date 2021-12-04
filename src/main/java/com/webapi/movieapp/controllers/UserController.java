package com.webapi.movieapp.controllers;

import com.webapi.movieapp.dtos.*;
import com.webapi.movieapp.exceptions.NotAuthorizedException;
import com.webapi.movieapp.exceptions.TokenExpiredException;
import com.webapi.movieapp.models.Role;
import com.webapi.movieapp.models.User;
import com.webapi.movieapp.security.UserDetailsServiceImpl;
import com.webapi.movieapp.security.models.AuthUserDetails;
import com.webapi.movieapp.security.models.AuthenticationRequest;
import com.webapi.movieapp.security.models.AuthenticationResponse;
import com.webapi.movieapp.service.ContentCommentService;
import com.webapi.movieapp.service.ContentService;
import com.webapi.movieapp.service.RoleService;
import com.webapi.movieapp.service.UserService;
import com.webapi.movieapp.util.JwtUtil;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ContentCommentService commentService;
    private final AuthenticationManager authenticationManager;
    private final ContentService contentService;
    private final UserDetailsServiceImpl userDetailsService;
    private final RoleService roleService;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ContentCommentService commentService,
                          AuthenticationManager authenticationManager, ContentService contentService,
                          UserDetailsServiceImpl userDetailsService, RoleService roleService,
                          JwtUtil jwtUtil, ModelMapper modelMapper) {
        this.userService = userService;
        this.commentService = commentService;
        this.authenticationManager = authenticationManager;
        this.contentService = contentService;
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add-user-role")
    public Role addRole(@RequestBody Role request) {
        return roleService.save(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<String>("Pogrešni kredencijali!", HttpStatus.BAD_REQUEST);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<AuthenticationResponse>(
                new AuthenticationResponse(jwt), HttpStatus.OK);
    }

    @GetMapping("/by-id")
    public UserDTO findById(@RequestParam int id) throws NotFoundException {
        return userService.findById(id, UserDTO.class);
    }

    @GetMapping("/details")
    public UserDTO getUser() throws NotFoundException {
        AuthUserDetails userDetails = (AuthUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findById(userDetails.getUserId(), UserDTO.class);
    }

    @GetMapping("/favourite-content")
    public List<ContentBaseDTO> getFavouriteContent() {
        AuthUserDetails authUserDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return contentService.getAllFavouriteByUser(authUserDetails.getUserId());
    }

    @PostMapping("/register")
    public User save(@RequestBody UserRequestDTO userDto) {
        return userService.save(userDto);
    }

    @PutMapping("/update")
    public User update(@RequestBody UserRequestDTO userDto) throws NotFoundException {
        return userService.update(userDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id) throws NotFoundException {
        userService.delete(id);
    }

//    @PostMapping("/forgot-password")
//    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDto) throws NotFoundException {
//        userService.forgotPassword(forgotPasswordDto);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PostMapping("/reset-password")
//    public User resetPassword(@RequestParam String token, @RequestBody ResetPasswordDTO resetPasswordDto) throws NotFoundException, TokenExpiredException {
//        return userService.resetPassword(token, resetPasswordDto);
//    }

    @PutMapping("/edit-comment")
    public ContentCommentDTO editComment(@RequestBody ContentCommentDTO request) throws NotFoundException, NotAuthorizedException {
        return commentService.editComment(request);
    }

    @DeleteMapping("/delete-comment")
    public void deleteComment(@RequestParam Integer commentId) throws NotFoundException, NotAuthorizedException {
        commentService.deleteComment(commentId);
    }


}
