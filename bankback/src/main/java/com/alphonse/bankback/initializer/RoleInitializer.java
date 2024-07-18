package com.alphonse.bankback.initializer;

import com.alphonse.bankback.constantes.Fonctionnalites;
import com.alphonse.bankback.entities.Permission;
import com.alphonse.bankback.entities.Role;
import com.alphonse.bankback.services.IPermissionService;
import com.alphonse.bankback.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Order(1)
@Component
public class RoleInitializer implements CommandLineRunner {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @Override
    public void run(String... args) throws Exception {

//        Role roleN1 = new Role();
//        roleN1.setCode("ADMIN");
//        roleN1.setNom("Administrateur De Banque");
//        roleService.save(roleN1);

//        if (roleService.findRoleByCode("ADMIN") == null) {
        /**
         * Verifions si le role existe ou pas, nous utilisons optional pour verifier si toute fois l'objet peut etre null
         */
        if (!roleService.findRoleByCode("ADMIN").isPresent()) {
            Role role1 = new Role();
            role1.setCode("ADMIN");
            role1.setNom(" Administrateur Gestion de banque ");
            Set<Permission> permissions = Arrays.asList(Fonctionnalites.values())
                    .stream().map(fonctionite -> {

                        Permission permission = new Permission();
                        permission.setCode(fonctionite.name());
                        permission.setLabel(fonctionite.getLabel());
                        return permission;

                    }).collect(Collectors.toSet());
            role1.setPermissions(permissionService.saveAll(permissions));

            roleService.save(role1);

        }

        roleService.listeRoles().stream()
                .forEach(role -> {
                    System.out.println(role.getId() + " " + role.getCode() + " " + role.getNom());
                });
    }
}
