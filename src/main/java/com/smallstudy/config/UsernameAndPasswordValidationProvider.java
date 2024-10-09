package com.smallstudy.config;

import com.smallstudy.validator.GlobalValidationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import static com.smallstudy.validator.GlobalValidationService.*;


@Slf4j
public class UsernameAndPasswordValidationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if(!emailValidate(username) || !passwordValidate(password))
        {
            log.info("UsernameAndPasswordValidationProvider ---> username password is invalid");
            throw new BadCredentialsException("Username or password format is invalid.");
        }
        return null;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
