package com.gd.clinic.config.security.repository;

import com.gd.clinic.config.security.entity.Role;
import com.gd.clinic.config.security.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName roleName);
}
