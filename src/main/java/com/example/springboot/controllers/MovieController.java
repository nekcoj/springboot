package com.example.springboot.controllers;

import com.example.springboot.entities.Movie;
import com.example.springboot.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {
  @Autowired
  private MovieService movieService;

  @GetMapping
  public Iterable<Movie> getAllMovies() {
    return movieService.getAllMovies();
  }

  @GetMapping("/{id}")
  public Movie getMovieById(@PathVariable String id){
    return movieService.getMovieById(id);
  }

  @PostMapping("/add")
  Movie addMovie(@RequestBody Movie movie){
    return movieService.addMovie(movie);
  }

}
