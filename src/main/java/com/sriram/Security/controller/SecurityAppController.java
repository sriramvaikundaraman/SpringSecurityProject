package com.sriram.Security.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sriram.Security.service.SecurityAppService;

@RestController
public class SecurityAppController {
	
	@Autowired
	SecurityAppService securityAppService;
	
	@PostConstruct
	public void initialize()
	{
		securityAppService.init();
	}
	
	@GetMapping(value = "/admin")
	@PreAuthorize("hasRole('Admin')")
	public String getAdmin()
	{
		return "Admin's homepage";
	}
	
	@GetMapping(value = "/user")
	@PreAuthorize("hasRole('User')")
	public String getUser()
	{
		return "User's homepage";
	}

}