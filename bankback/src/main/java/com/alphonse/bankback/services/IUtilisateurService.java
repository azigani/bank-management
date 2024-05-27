package com.alphonse.bankback.services;

import com.alphonse.bankback.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface IUtilisateurService {

    Utilisateur save(Utilisateur utilisateur);
    Optional<Utilisateur> findById(Long id);
    Optional<Utilisateur> findByLogin(String login);
    List<Utilisateur> listeUtilisateurs();

    List<Utilisateur> findUtilisateurByRoleCode(String code);
}
