package com.alphonse.bankback.services.servicesImpl;

import com.alphonse.bankback.dao.UtilisateurRepository;
import com.alphonse.bankback.entities.Utilisateur;
import com.alphonse.bankback.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements IUtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurdao;

    public UtilisateurServiceImpl() {
    }

    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        Utilisateur utilisateursave = utilisateurdao.save(utilisateur);
        /**
         * Retourne l'objet enregistre
         */
        return utilisateursave;

    }

    @Override
    public Optional<Utilisateur> findById(Long id) {
        return utilisateurdao.findById(id);
    }

    @Override
    public Optional<Utilisateur> findByLogin(String login) {
        return utilisateurdao.findByLogin(login);
    }

    @Override
    public List<Utilisateur> listeUtilisateurs() {
        return Streamable.of(utilisateurdao.findAll())
                .toList();
    }

    @Override
    public List<Utilisateur> findUtilisateurByRoleCode(String code) {
        return utilisateurdao.findUtilisateurByRoleCode(code);
    }

}
