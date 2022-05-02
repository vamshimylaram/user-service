package com.sony.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sony.demo.entities.User;
import com.sony.demo.repositories.UserRepository;

@Service
public class UserService {

  @Autowired private UserRepository userRepository;

  @Transactional(readOnly = true)
  public User findUserById(String id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new IllegalStateException("no user with id " + id));
  }

  @Transactional
  public User upsertUser(User user) {
    return userRepository.save(user);
  }

  @Transactional
  public void deleteUser(String id) {
    userRepository.deleteById(id);
  }
}
