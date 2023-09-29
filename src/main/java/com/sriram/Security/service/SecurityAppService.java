package com.sriram.Security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sriram.Security.dao.EmployeeDao;
import com.sriram.Security.entity.Employee;
import com.sriram.Security.entity.Role;

@Service
public class SecurityAppService {
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void init()
	{
		Role role1 = new Role("Admin","Role for admin");
		Role role2 = new Role("User", "User for user");
		List<Role> roleList1 = new ArrayList<>();
		roleList1.add(role1);
		List<Role> roleList2 = new ArrayList<>();
		roleList2.add(role2);
		Employee emp1 = new Employee("Sriram",getEncodedPassword("admin"), roleList1);
		Employee emp2 = new Employee( "Yogesh", getEncodedPassword("user"), roleList2);
		employeeDao.save(emp1);
		employeeDao.save(emp2);
		
	}
	
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

}
