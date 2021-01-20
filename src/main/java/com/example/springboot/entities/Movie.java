package com.example.springboot.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Movie {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String imdbID;
  @JsonProperty("Title")
  private String title;
  private float imdbRating;
  @JsonProperty("Plot")
  private String plot;
}
