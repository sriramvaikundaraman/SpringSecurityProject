package com.sriram.Security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sriram.Security.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, String>{

}
