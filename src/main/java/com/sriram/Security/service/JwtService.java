package com.sriram.Security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sriram.Security.dao.EmployeeDao;
import com.sriram.Security.entity.Employee;
import com.sriram.Security.entity.JwtRequest;
import com.sriram.Security.entity.JwtResponse;
import com.sriram.Security.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JwtService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception
	{
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
		Employee temp = employeeDao.findById(userName).get();

		logger.info(temp.getEmpName());
		authenticate(userName, userPassword);
		final UserDetails userDetails=  loadUserByUsername(userName);
		logger.info("UserDetails framed");
		String newToken = jwtUtil.generateToken(userDetails);
		logger.info(newToken);

		Employee employee = employeeDao.findById(userName).get();
		return new JwtResponse(employee, newToken);
	}
	
	private void authenticate(String userName, String userPassword) throws Exception
	{
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		}
		catch(DisabledException e)
		{
			throw new Exception("User is disabled");
		}
		catch (BadCredentialsException e) {
			throw new Exception("Bad Credentials exception");
		}
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Employee employee = employeeDao.findById(username).get();
		logger.info("**********************");
		logger.info(employee.getEmpName());
		if(employee != null )
		{
			return new User(employee.getEmpName(), employee.getPassword(), getAuthorities(employee));
		}
		else {
			throw new UsernameNotFoundException("Username is not valid");
		}
	}
	
	private Set getAuthorities(Employee employee)
	{
		Set authorities = new HashSet();
		employee.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		});
		
		return authorities;
	}
	

}
