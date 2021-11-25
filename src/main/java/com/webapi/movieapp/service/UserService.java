package com.webapi.movieapp.service;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.dtos.ForgotPasswordDTO;
import com.webapi.movieapp.dtos.ResetPasswordDTO;
import com.webapi.movieapp.dtos.UserDTO;
import com.webapi.movieapp.exceptions.TokenExpiredException;
import com.webapi.movieapp.models.*;
import com.webapi.movieapp.repositories.PasswordResetTokenRepository;
import com.webapi.movieapp.repositories.RoleRepository;
import com.webapi.movieapp.repositories.UserRepository;
import com.webapi.movieapp.repositories.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class UserService extends CrudJpaService<User, Integer> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailService emailService;
    private final PasswordResetTokenService passwordResetTokenService;
    private final ModelMapper modelMapper;

    @Value("${server.port}")
    int serverPort;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       UserRoleRepository userRoleRepository, @Lazy PasswordEncoder passwordEncoder,
                       PasswordResetTokenRepository passwordResetTokenRepository, EmailService emailService,
                       PasswordResetTokenService passwordResetTokenService, ModelMapper modelMapper) {
        super(userRepository, modelMapper, User.class);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.emailService = emailService;
        this.passwordResetTokenService = passwordResetTokenService;
        this.modelMapper = modelMapper;
    }

    public User save(UserDTO userDto) {
        User user = super.insert(buildUserFromDto(userDto), User.class);
        userDto.getRoleIds().forEach(roleId -> {
            UserRoleId key = new UserRoleId(user.getUserId(), roleId);
            Role role = roleRepository.getById(roleId);
            userRoleRepository.save(new UserRole(key, user, role));
        });
        return userRepository.save(user);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User update(UserDTO userDto) throws NotFoundException {
        if (userRepository.existsById(userDto.getUserId())) {
            userRoleRepository.deleteByUser_UserId(userDto.getUserId());
            User user = super.update(userDto.getUserId(), buildUserFromDto(userDto), User.class);
            userDto.getRoleIds().forEach(roleId -> {
                UserRoleId key = new UserRoleId(user.getUserId(), roleId);
                Role role = roleRepository.getById(roleId);
                userRoleRepository.save(new UserRole(key, user, role));
            });
            return user;
        }
        throw new NotFoundException("Not found user by id:" + userDto.getUserId());
    }

    public void delete(int id) throws NotFoundException {
        User user = super.findById(id, User.class);
        user.setActive(false);
        userRepository.save(user);
    }

    private User buildUserFromDto(UserDTO userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setActive(true);
        return user;
    }

    public void forgotPassword(ForgotPasswordDTO forgotPasswordDto) throws NotFoundException {
        User user = userRepository.findByEmail(forgotPasswordDto.getEmail())
                .orElseThrow(() -> new NotFoundException("Nije pronađen korisnik sa email-om:" + forgotPasswordDto.getEmail()));
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUser(user);
        passwordResetToken.setToken(token);
        passwordResetToken.setExpiryDate(LocalDateTime.now().plusHours(12));
        passwordResetTokenRepository.save(passwordResetToken);

        String message = "http://" +
                InetAddress.getLoopbackAddress().getHostAddress() +
                ":" + serverPort + "/user/reset-password?token=" + token;
        emailService.sendSimpleMessage(user.getEmail(), "MovieApp - resetovanje lozinke", message);
    }

    public User resetPassword(String token, ResetPasswordDTO resetPasswordDto) throws NotFoundException, TokenExpiredException {
        PasswordResetToken passwordResetToken = passwordResetTokenService.findByToken(token);
        passwordResetTokenService.isTokenExpired(token);

        User user = passwordResetToken.getUser();
        user.setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));
        userRepository.save(user);

        passwordResetToken.setExpiryDate(LocalDateTime.now());
        passwordResetTokenRepository.save(passwordResetToken);

        return user;
    }

}
