package com.alphonse.bankback.securite.services;

import com.alphonse.bankback.entities.Utilisateur;
import com.alphonse.bankback.securite.UserDetailsImpl;
import com.alphonse.bankback.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUtilisateurService utilisateurService;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<Utilisateur> utilisateurOptional =utilisateurService.findByLogin(username);
        if (utilisateurOptional.isPresent()){
            return new UserDetailsImpl(utilisateurOptional.get());
        }
        return null;
    }
}
