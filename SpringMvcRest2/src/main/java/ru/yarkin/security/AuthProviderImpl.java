package ru.yarkin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ru.yarkin.models.database.User;
import ru.yarkin.service.UserService;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        User user = userService.getOne(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getName());

        String password = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
        System.out.println(userDetails.getAuthorities().toString());
        return new UsernamePasswordAuthenticationToken(user, password, userDetails.getAuthorities());


    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
