package com.sriram.Security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sriram.Security.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, String>{

}
