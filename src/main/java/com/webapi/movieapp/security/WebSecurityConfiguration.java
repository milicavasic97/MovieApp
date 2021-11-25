package com.webapi.movieapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    private static final String[] ADMIN_URLS = {
            "/movie-roles/**", "/movie-people/**", "/languages/**",
            "/content-types/**", "/countries/**", "/genres/**",
            "/contents/**"
    };

    private static final String[] USER_URLS = {
            "/contents/details", "/contents/mark-favourite", "/contents/remark-favourite",
            "/contents/rate", "/contents/comment", "/contents/comments",
            "/users/rating", "/users/update",
            "/users/edit-comment", "/users/delete-comment", "/users/favourite-content",
            "/movies/by-category", "/movies/by-release-date",
            "/movies/by-rating", "/movies/by-genre", "/movies/favourites",
            "/series/by-category", "/series/by-release-date",
            "/series/by-rating", "/series/by-genre", "/series/favourites",
            "/genres/all"
    };

    private static final String[] PERMIT_ALL_URLS = {
            "/users/register", "/users/login", "/users/forgot-password", "/users/reset-password",
            "/users/add-user-role",
            // -- Swagger UI v2
            "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
            "/configuration/security", "/swagger-ui.html", "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**", "/swagger-ui/**", "/actuator/**" };

    public WebSecurityConfiguration(UserDetailsServiceImpl userDetailsService,
                                    JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    // authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(PERMIT_ALL_URLS).permitAll()
                .antMatchers(ADMIN_URLS).hasRole("ADMIN")
                .antMatchers(USER_URLS).hasAnyRole("USER")
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        // @formatter:on
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

}
