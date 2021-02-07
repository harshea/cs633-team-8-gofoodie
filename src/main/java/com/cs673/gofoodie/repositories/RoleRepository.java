package com.cs673.gofoodie.repositories;

import com.cs673.gofoodie.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
  Role findByRole(final String role);

}
