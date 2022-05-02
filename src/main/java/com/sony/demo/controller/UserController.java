package com.sony.demo.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sony.demo.entities.User;
import com.sony.demo.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired private UserService userService;

  @GetMapping("/{userId}")
  public User getUserById(@PathVariable String userId) {
    return userService.findUserById(userId);
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public User postUser(@Valid @RequestBody User user) {
	validateIdForPost(user);
	return userService.upsertUser(user);
  }

  @PutMapping("/{userId}")
  @ResponseStatus(NO_CONTENT)
  public void putUser(@PathVariable String userId, @Valid @RequestBody User user) {
    validateIdForPut(userId, user);
    user.setId(userId);
	userService.upsertUser(user);
  }

  @DeleteMapping("/{userId}")
  @ResponseStatus(NO_CONTENT)
  public void deleteUser(@PathVariable String userId) {
    userService.deleteUser(userId);
  }
  
  private void validateIdForPut(String pathId, User user) {
    String dtoId = user.getId();
    if (!pathId.equals(dtoId) && dtoId != null) {
      String error = "PUT entity id '%s' must either be unset (null) or the same as the path parameter '%s'";
      throw new ValidationException(String.format(error, dtoId, pathId));
    }
  }

  private void validateIdForPost(User user) {
    if (user.getId() != null) {
      throw new ValidationException("POSTed entity should not have an id, did you mean to PUT?");
    }
  }
}
