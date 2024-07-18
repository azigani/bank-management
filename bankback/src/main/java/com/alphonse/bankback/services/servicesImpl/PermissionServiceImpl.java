package com.alphonse.bankback.services.servicesImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.alphonse.bankback.constantes.Fonctionnalites;
import com.alphonse.bankback.dao.PermissionDao;
import com.alphonse.bankback.dto.FonctionnalityDto;
import com.alphonse.bankback.entities.Permission;
import com.alphonse.bankback.entities.Role;
import com.alphonse.bankback.securite.UserDetailsImpl;
import com.alphonse.bankback.services.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class PermissionServiceImpl implements IPermissionService {
	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public Permission save(Permission permission) {
		return permissionDao.save(permission);
	}

	@Override
	public Set<Permission> findAll() {
		return Streamable.of(permissionDao.findAll()).toSet();
	}

	@Override
	public Set<Permission> saveAll(Set<Permission> permissions) {
		Iterable<Permission> saved = permissionDao.saveAll(permissions);
		return Streamable.of(saved).toSet();
	}

	@Override
	public List<FonctionnalityDto> getFonctionnalites() {
		return Arrays.stream(Fonctionnalites.values())
				.map(fonctionnalite -> {
					return new FonctionnalityDto(){
						{
							setName(fonctionnalite.name());
							setLabel(fonctionnalite.getLabel());
						}
					};
				})
				.collect(Collectors.toList());
	}


}

