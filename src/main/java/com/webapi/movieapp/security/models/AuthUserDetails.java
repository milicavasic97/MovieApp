package com.webapi.movieapp.security.models;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.webapi.movieapp.models.User;
import com.webapi.movieapp.models.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUserDetails implements UserDetails {
    private Integer userId;
    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public AuthUserDetails() {

    }

    public AuthUserDetails(Optional<User> user, List<UserRole> userRoles) {
        this.userId = user.get().getUserId();
        this.username = user.get().getUsername();
        this.password = user.get().getPassword();
        this.active = true;
        this.authorities = userRoles.stream().map(e -> new SimpleGrantedAuthority(e.getRole().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getUserId() { return userId; }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
