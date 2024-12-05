package com.it.ebanking.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws BadCredentialsException {
        String username = authentication.getName();  // Récupérer le nom d'utilisateur
        String password = authentication.getCredentials().toString();  // Récupérer le mot de passe (peu importe le mot de passe)

        // Vérifier si l'utilisateur existe dans la base de données
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails != null) {
            // Si l'utilisateur existe, créer un token d'authentification avec le nom d'utilisateur et n'importe quel mot de passe
            return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        } else {
            // Si l'utilisateur n'existe pas dans la base de données, lancer une exception d'authentification
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);  // Support uniquement les tokens de type UsernamePasswordAuthenticationToken
    }

}

