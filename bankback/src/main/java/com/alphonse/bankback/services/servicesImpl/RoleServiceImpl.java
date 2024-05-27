package com.alphonse.bankback.services.servicesImpl;

import com.alphonse.bankback.dao.RoleRepository;
import com.alphonse.bankback.entities.Role;
import com.alphonse.bankback.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roledao;

    public RoleServiceImpl() {
    }

    @Override
    public Role save(Role role) {
         Role rolesave =roledao.save(role);
         /**
          * Retourne l'objet enregistre
          */
        return rolesave;

    }

    @Override
    public Optional<Role> findById(Long id) {
        return roledao.findById(id);
    }


    @Override
    public List<Role> listeRoles() {
        return Streamable.of(roledao.findAll())
                .toList();

//        return roledao.findAll();
    }

    @Override
    public Optional<Role> findRoleByCode(String code) {
        return roledao.findRoleByCode(code);
    }


}
