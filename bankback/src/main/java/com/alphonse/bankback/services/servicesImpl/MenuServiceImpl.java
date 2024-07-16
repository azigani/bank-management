package com.alphonse.bankback.services.servicesImpl;

import com.alphonse.bankback.constantes.Fonctionnalites;
import com.alphonse.bankback.dto.LinkDto;
import com.alphonse.bankback.services.IMenuService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MenuServiceImpl implements IMenuService {

    @Override
    public List<LinkDto> getMenu() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LinkDto linkDto1 = new LinkDto();
        linkDto1.setCode(Fonctionnalites.GESTION_JOURNAUX.getPath());
        linkDto1.setCode(Fonctionnalites.GESTION_JOURNAUX.name());
        linkDto1.setLibelle(Fonctionnalites.GESTION_JOURNAUX.getLabel());

        LinkDto linkDto2 = new LinkDto();
        linkDto2.setCode(Fonctionnalites.GESTION_UTILISATEURS.getPath());
        linkDto2.setCode(Fonctionnalites.GESTION_UTILISATEURS.name());
        linkDto2.setLibelle(Fonctionnalites.GESTION_UTILISATEURS.getLabel());

        List<LinkDto> liens = new ArrayList<>();

        liens.add(linkDto1);
        liens.add(linkDto2);
        return liens;
    }

}
