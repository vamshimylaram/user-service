package com.sony.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication
public class UserServiceApplication {

  public static void main(String[] args) {
    var app = new SpringApplication(UserServiceApplication.class);

    // if no active profiles are set assume we're running locally
    var env = new StandardEnvironment();
    env.getPropertySources().addFirst(new SimpleCommandLinePropertySource(args));
    if (env.getActiveProfiles().length == 0) {
      app.setAdditionalProfiles("local", "override");
    }
    app.run(args);
  }
}
