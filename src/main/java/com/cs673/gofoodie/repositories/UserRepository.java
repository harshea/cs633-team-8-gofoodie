package com.cs673.gofoodie.repositories;

import com.cs673.gofoodie.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(final String email);
}
