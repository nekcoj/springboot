package com.example.springboot.repositories;

import com.example.springboot.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, String> {
  User findUserByUsername( String username );
}
