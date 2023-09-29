package com.sriram.Security.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(schema="security")
public class Employee {

	@Id
	private String empName;
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "employee_role_map", schema = "security",
			joinColumns = @JoinColumn(name="emp_id"),
			inverseJoinColumns = @JoinColumn(name="role_name"))
	private List<Role> role;

	public Employee() {

	}

	public Employee( String empName, String password, List<Role> role) {
		super();
		this.empName = empName;
		this.password = password;
		this.role = role;
	}




	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", password=" + password + ", role=" + role + "]";
	}


	
	
	
	
	

}
