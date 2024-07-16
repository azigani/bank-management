package com.alphonse.bankback.initializer;

import com.alphonse.bankback.entities.Credentials;
import com.alphonse.bankback.entities.Role;
import com.alphonse.bankback.entities.Utilisateur;
import com.alphonse.bankback.services.ICredentialsService;
import com.alphonse.bankback.services.IRoleService;
import com.alphonse.bankback.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Order(2)
@Component
@Profile("dev")
public class UtilisateurInitializer implements CommandLineRunner {
    @Autowired
    IRoleService roleService;
    @Autowired
    IUtilisateurService userService;
    @Autowired
    ICredentialsService credentialsService;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        Optional<Utilisateur> utilisateurOptional = userService.findByLogin("admin");
        if (!utilisateurOptional.isPresent()) {
            Optional<Role> roleAdminOptional = roleService.findRoleByCode("ADMIN");

            if (roleAdminOptional.isPresent()) {
                Utilisateur utilisateurAdmin = new Utilisateur();
                utilisateurAdmin.setRole(roleAdminOptional.get());
                utilisateurAdmin.setEmail("azigani8@gmail.com");
                utilisateurAdmin.setUsername("admin@123");
                utilisateurAdmin.setLogin("admin");
//            utilisateurAdmin.setLogin("admin@123");
                userService.save(utilisateurAdmin);

                /**
                 * Creation du credential
                 */
                Credentials credentials = new Credentials();
                String encodePassword = encoder.encode("12345");
                credentials.setPassword(encodePassword);
                credentials.setLogin(utilisateurAdmin.getLogin());
                credentials.setAccountNonExpired(true);
                credentials.setAccountNonLocked(true);
                credentials.setCredentialsNonExpired(true);
                credentials.setEnabled(true);

                /**
                 * Enregistrer le credential
                 */
                credentialsService.save(credentials);


            }
        }


        //find All
        roleService.listeRoles()
                .stream()
                .forEach(role -> {
                    System.out.println(role.getId() + " ," + role.getId() + " ," + role.getCode() + " ," + role.getNom());
                });

    }


}
