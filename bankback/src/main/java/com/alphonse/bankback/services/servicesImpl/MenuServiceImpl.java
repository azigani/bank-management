package com.alphonse.bankback.services.servicesImpl;

import com.alphonse.bankback.constantes.Fonctionnalites;
import com.alphonse.bankback.dto.LinkDto;
import com.alphonse.bankback.services.IMenuService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class MenuServiceImpl implements IMenuService {

    @Override
    public List<LinkDto> getMenu() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        List<GrantedAuthority> autorities = (List<GrantedAuthority>) authentication.getAuthorities();
        /**
         * La liste est initialisée ici
         */
        List<LinkDto>    liens = new ArrayList<>();
        /**
         * il faut disposer de role admin pour faire cela
         */
//        boolean isAdmin = autorities.stream().anyMatch(authority -> "ADMIN".equals(authority.getAuthority()));

        if (findPermission("ADMIN",authentication.getAuthorities()))
         {

//            LinkDto linkDto1 = new LinkDto();
//            linkDto1.setCode(Fonctionnalites.GESTION_JOURNAUX.getPath());
//            linkDto1.setCode(Fonctionnalites.GESTION_JOURNAUX.name());
//            linkDto1.setLibelle(Fonctionnalites.GESTION_JOURNAUX.getLabel());
//
//            LinkDto linkDto2 = new LinkDto();
//            linkDto2.setCode(Fonctionnalites.GESTION_UTILISATEURS.getPath());
//            linkDto2.setCode(Fonctionnalites.GESTION_UTILISATEURS.name());
//            linkDto2.setLibelle(Fonctionnalites.GESTION_UTILISATEURS.getLabel());

//            List<LinkDto> liens = new ArrayList<>();

            liens.add(convertFonctionaltyToLinkDto(Fonctionnalites.GESTION_UTILISATEURS));
            liens.add(convertFonctionaltyToLinkDto(Fonctionnalites.GESTION_JOURNAUX));
            liens.add(convertFonctionaltyToLinkDto(Fonctionnalites.GESTION_PERMISSIONS));
        }
        return liens;
    }

    private boolean findPermission(String permission, Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().filter(authority -> permission.equals(authority.getAuthority())).count()>0;
    }

    /**
     *
     * @param fonctionality
     * @return link
     */
    private LinkDto convertFonctionaltyToLinkDto(Fonctionnalites fonctionality) {
        LinkDto link = new LinkDto();
        link.setPath(fonctionality.getPath());
        link.setCode(fonctionality.name());
        link.setLibelle(fonctionality.getLabel());
        return link;
    }

}
