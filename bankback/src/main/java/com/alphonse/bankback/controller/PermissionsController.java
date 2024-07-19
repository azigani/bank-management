package com.alphonse.bankback.controller;


import com.alphonse.bankback.dto.FonctionnalityDto;
import com.alphonse.bankback.entities.Role;
import com.alphonse.bankback.services.IPermissionService;
import com.alphonse.bankback.services.IRoleService;
import com.alphonse.bankback.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/permissions")
@PreAuthorize("hasAuthority('GESTION_PERMISSIONS')")
public class PermissionsController {
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IPermissionService permissionService;

//	@GetMapping("/user-role")
//	public ResponseEntity<Role> getRole(){
//		return ResponseEntity.ok(permissionService.getUserRole());
//	}

	@GetMapping("/roles")
	public ResponseEntity<List<Role>> getRoles(){
		return ResponseEntity.ok(roleService.listeRoles());
	}

	
	@PostMapping("/add-role")
	@Trace
	public ResponseEntity<?> addRole(@RequestBody Role role){
		if(role != null) {
			roleService.save(role);
			return ResponseEntity.ok(ResponseEntity.ok());
		}
		
		return ResponseEntity.ok(ResponseEntity.badRequest()
				.build()
				.getStatusCode());
		
	}
	
	@PostMapping("/save-role")
	@Trace
	public ResponseEntity<?> saveRole(@RequestBody Role role){
		if(role != null) {
			role.setPermissions(permissionService.saveAll(role.getPermissions()));
			roleService.save(role);
			return ResponseEntity.ok(ResponseEntity.ok());
		}
		
		return ResponseEntity.ok(ResponseEntity.badRequest()
				.build()
				.getStatusCode());
		
	}

	@GetMapping("/fonctionnalites")
	public ResponseEntity<List<FonctionnalityDto>> getFonctionnalites(){
		List<FonctionnalityDto> fonctionnalites = permissionService.getFonctionnalites();
		return ResponseEntity.ok(fonctionnalites);
	}



}
