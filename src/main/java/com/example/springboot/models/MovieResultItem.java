package com.example.springboot.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MovieResultItem {
  @JsonProperty("Title")
  private String title;
  @JsonProperty("imdbID")
  private String imdbID;
  @JsonProperty("imdbRating")
  private float imdbRating;
  @JsonProperty("Plot")
  private String plot;
}
