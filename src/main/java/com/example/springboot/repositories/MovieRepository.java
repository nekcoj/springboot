package com.example.springboot.repositories;

import com.example.springboot.entities.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {
  Optional<Movie> findMovieByImdbID(String imdbID);
}
