package com.gd.clinic.security.repository;

import com.gd.clinic.security.entity.Role;
import com.gd.clinic.security.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(RoleName roleName);
}
