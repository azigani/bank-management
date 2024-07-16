package com.alphonse.bankback.controller;
import com.alphonse.bankback.dto.LinkDto;
import com.alphonse.bankback.services.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/menu")
public class MenuController {
	@Autowired
	private IMenuService menuService;
	
	@GetMapping
	public ResponseEntity<List<LinkDto>> getMenu(){

		return ResponseEntity.ok(menuService.getMenu());
	}

}
