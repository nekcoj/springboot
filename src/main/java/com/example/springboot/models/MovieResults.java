package com.example.springboot.models;

import lombok.Data;
import java.util.List;

@Data
public class MovieResults {
  private List<MovieResultItem> results;
}
