package com.example.springboot.services;

import com.example.springboot.entities.User;
import com.example.springboot.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
public class MyUserDetailsService implements UserDetailsService {

  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
  public BCryptPasswordEncoder getEncoder() { return encoder; }

  @Autowired
  private UserRepo userRepo;

  @PostConstruct
  private void createDefaultUsers(){
    if (userRepo.findUserByUsername("jocke") == null) {
      addUser("jocke", "1234");
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("HOPPSAN!!!!!");
    User user = userRepo.findUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found by name: " + username);
    }
    return toUserDetails(user);
  }

  public User addUser(String username, String password){
    User user = new User(username, encoder.encode(password));
    try {
      return userRepo.save(user);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  private UserDetails toUserDetails(User user) {
    // If you have a User entity you have to
    // use the userdetails User for this to work
    return org.springframework.security.core.userdetails.User
        .withUsername(user.getUsername())
        .password(user.getPassword())
        .roles("USER").build();
  }
}
