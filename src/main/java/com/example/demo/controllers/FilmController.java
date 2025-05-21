package com.example.demo.controllers;

import com.example.demo.classes.Movie;
import com.example.demo.repositories.MovieRepository;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/getMovieData")
    @CrossOrigin
    public ResponseEntity<Movie> getMovieData(@RequestParam Long id){
        Movie currmovie = repo.getMovieById(id);
        return ResponseEntity.ok(currmovie);
    }
    @GetMapping("/findMovies")
    @CrossOrigin
    public ResponseEntity<List<Movie>> findMovies(@RequestParam String name){
        return ResponseEntity.ok(repo.findAllByNameContains(name));
    }
}
