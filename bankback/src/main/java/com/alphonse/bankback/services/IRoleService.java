package com.alphonse.bankback.services;

import com.alphonse.bankback.entities.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    Role  save(Role role);
    Optional<Role> findById(Long id);
    List<Role> listeRoles();

    Optional<Role> findRoleByCode(String code);
}
