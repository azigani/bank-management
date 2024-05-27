package com.alphonse.bankback.dao;

import com.alphonse.bankback.entities.Role;
import com.alphonse.bankback.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByLogin(String login);

    List<Utilisateur> findUtilisateurByRoleCode(String code);

}
