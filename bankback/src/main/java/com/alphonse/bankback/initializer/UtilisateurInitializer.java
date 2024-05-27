package com.alphonse.bankback.initializer;

import com.alphonse.bankback.entities.Role;
import com.alphonse.bankback.entities.Utilisateur;
import com.alphonse.bankback.services.IRoleService;
import com.alphonse.bankback.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Order(2)
@Component
public class UtilisateurInitializer implements CommandLineRunner {
    @Autowired
    IRoleService roleService;
    @Autowired
    IUtilisateurService userService;

    @Override
    public void run(String... args) throws Exception {
////verifier si ya pas de role admin on insert
//
//       Optional <Utilisateur> userAdminOpt = userService.findByLogin("admin");
////        if (userAdminOpt==null){
//        if (userAdminOpt.isPresent()){
//            Optional <Role> roleAdmin = roleService.findRoleByCode("ADMIN");
////            if (roleAdmin != null) {
//            if (!roleAdmin.isPresent()) {
//                /**
//                 * On ne peut enregistrer qu'un user si le role n'est pas trouvé dans la BD
//                 */
//                Utilisateur userAdmin = new Utilisateur();
//                //renseigner le code du role
//                userAdmin.setRole(roleAdmin);
//                userAdmin.setEmail("admin@gmail.com");
//                userAdmin.setUsername("alphonse");
//                userAdmin.setLogin("admin");
//                userService.save(userAdmin);
//            }
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
