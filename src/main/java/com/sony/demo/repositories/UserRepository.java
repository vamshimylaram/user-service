package com.sony.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sony.demo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {}
