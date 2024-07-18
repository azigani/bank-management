package com.alphonse.bankback.services;

import java.util.List;
import java.util.Set;

import com.alphonse.bankback.dto.FonctionnalityDto;
import com.alphonse.bankback.entities.Permission;
public interface IPermissionService {
	Permission save(Permission permission);
	Set<Permission> findAll();
	Set<Permission> saveAll(Set<Permission> permissions);
//	Role getUserRole();

	List<FonctionnalityDto> getFonctionnalites();
}
