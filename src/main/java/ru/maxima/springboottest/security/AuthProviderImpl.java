package ru.maxima.springboottest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthProviderImpl implements AuthenticationProvider {

    private final PersonDetailsService service;

    @Autowired
    public AuthProviderImpl(PersonDetailsService service) {
        this.service = service;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        UserDetails personDetails = service.loadUserByUsername(username);

        String password = authentication.getCredentials().toString();

        if (!password.equals(personDetails.getPassword())) {
            throw new BadCredentialsException("In maxima-app it is incorrect password");
        }



        return new UsernamePasswordAuthenticationToken(personDetails, password,
                Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
