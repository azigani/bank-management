package com.alphonse.bankback.securite.services;

import com.alphonse.bankback.entities.Credentials;
import com.alphonse.bankback.entities.Utilisateur;
import com.alphonse.bankback.securite.UserDetailsImpl;
import com.alphonse.bankback.services.ICredentialsService;
import com.alphonse.bankback.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private IUtilisateurService utilisateurService;
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//        Optional<Utilisateur> utilisateurOptional =utilisateurService.findByLogin(username);
//        if (utilisateurOptional.isPresent()){
//            return new UserDetailsImpl(utilisateurOptional.get());
//        }
//        return null;
//    }

     @Autowired
   private IUtilisateurService utilisateurService;

    @Autowired
    private ICredentialsService credentialsService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Utilisateur> userOptional = utilisateurService.findByLogin(username);
//        if(userOptional.isPresent()) {
//            Optional<Credentials> optionalCredentials = credentialsService.findByLogin(username);
//            if(optionalCredentials.isPresent()) {
//                return new UserDetailsImpl(userOptional.get(), optionalCredentials.get());
//            }
//        }
//        return null;
//    }

    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utilisateur> userOptional = utilisateurService.findByLogin(username);
        if (userOptional.isPresent()) {
            Optional<Credentials> optionalCredentials = credentialsService.findByLogin(username);
            if (optionalCredentials.isPresent()) {
                return new UserDetailsImpl(userOptional.get(), optionalCredentials.get());
            }
        }
        // Lever une exception UsernameNotFoundException au lieu de renvoyer null
        throw new UsernameNotFoundException("User not found with username: " + username);
    }


}
