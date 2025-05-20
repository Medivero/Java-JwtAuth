package com.example.demo.controllers;

import com.example.demo.classes.Movie;
import com.example.demo.repositories.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class FilmController {
    MovieRepository repo;

    public FilmController(MovieRepository repo){
        this.repo = repo;
    }

    @CrossOrigin
    @GetMapping("/getAllMovies")
    public List<Movie> getAllMovies(){
        return repo.findAll();
    }
    @PostMapping("/addNewMovie")
    @CrossOrigin
    public void addNewMovie(@RequestBody Movie movie) {
        repo.save(movie);
    }
}
