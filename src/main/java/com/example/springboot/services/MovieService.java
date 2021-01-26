package com.example.springboot.services;

import com.example.springboot.entities.Movie;
import com.example.springboot.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class MovieService {
  private final RestTemplate restTemplate = new RestTemplate();
  private final String apiKey = "13653be9";

  @Autowired
  private MovieRepository movieRepository;

  public Iterable<Movie> getAllMovies() {
    Iterable<Movie> iterable = movieRepository.findAll();
    if(iterable.iterator().hasNext()){
      return iterable;
    }

    return null;
  }

  public Movie getMovieById(String id) {
    Optional<Movie> result;
    if(id.contains("tt")){
       result = movieRepository.findMovieByImdbID(id);
    } else {
      result = movieRepository.findById(Long.parseLong(id));
    }
    if(result.isPresent()){
      return result.get();
    }

    Movie movie = restTemplate.getForObject("http://www.omdbapi.com/?apikey="+ apiKey + "&r=json&plot=full&i=" + id, Movie.class);
    assert movie != null;
    if(movie.getTitle() != null){
      movieRepository.save(movie);
      return movie;
    }
    return new Movie(-1L, "Id not found", "The id you have provided returned null.", -1, null, "https://upload.wikimedia.org/wikipedia/commons/a/ac/No_image_available.svg");
  }

  public Movie addMovie(Movie movie) {
    return movieRepository.save(movie);
  }
}
