package com.webapi.movieapp.service;

import com.webapi.movieapp.exceptions.TokenExpiredException;
import com.webapi.movieapp.models.PasswordResetToken;
import com.webapi.movieapp.repositories.PasswordResetTokenRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PasswordResetTokenService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    public PasswordResetToken findByToken(String token) throws NotFoundException {
        return passwordResetTokenRepository.findByToken(token).orElseThrow(() -> new NotFoundException("Nevalidan token za reset lozinke!"));
    }

    public void isTokenExpired(String token) throws NotFoundException, TokenExpiredException {
        PasswordResetToken passwordResetToken = findByToken(token);
        if (passwordResetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException(passwordResetToken.getExpiryDate().toString());
        }
    }
}
