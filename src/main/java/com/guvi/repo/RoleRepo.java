package com.guvi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guvi.entity.Role;
import com.guvi.entity.RolePK;
@Repository
public interface RoleRepo extends JpaRepository<Role,RolePK > {

}
