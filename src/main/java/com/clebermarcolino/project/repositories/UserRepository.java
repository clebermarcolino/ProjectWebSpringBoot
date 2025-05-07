package com.clebermarcolino.project.repositories;

import com.clebermarcolino.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
