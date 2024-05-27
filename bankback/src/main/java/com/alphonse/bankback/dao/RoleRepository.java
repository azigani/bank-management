package com.alphonse.bankback.dao;

import com.alphonse.bankback.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByCode(String code);

//    Optional<Role> findByLogin(String login);
}
