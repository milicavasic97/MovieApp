package com.webapi.movieapp.security;

import java.util.List;
import java.util.Optional;

import com.webapi.movieapp.models.User;
import com.webapi.movieapp.models.UserRole;
import com.webapi.movieapp.repositories.UserRoleRepository;
import com.webapi.movieapp.security.models.AuthUserDetails;
import com.webapi.movieapp.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final UserRoleRepository userRoleRepository;

    public UserDetailsServiceImpl(UserService userService, UserRoleRepository userRoleRepository) {
        this.userService = userService;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userService.findUserByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Nije pronađen korisnik sa username-om:" + username));
        List<UserRole> userRoles = userRoleRepository.findByUser(user.get());

        return new AuthUserDetails(user, userRoles);
    }

}

