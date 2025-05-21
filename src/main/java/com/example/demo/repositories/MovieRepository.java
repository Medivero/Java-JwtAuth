package com.example.demo.repositories;

import com.example.demo.classes.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    Movie findMovieByName(String name);

    Movie getMovieByName(String moviename);

    List<Movie> findAllByNameContains(String name);

    Movie getMovieById(Long id);

    boolean existsByNameContains(String name);

    boolean existsByName(String name);
}
