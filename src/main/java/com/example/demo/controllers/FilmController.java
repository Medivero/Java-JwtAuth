package com.example.demo.controllers;

import com.example.demo.classes.Movie;
import com.example.demo.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/getAllMovies")
    public List<Movie> getAllMovies(){
        return repo.findAll();
    }
    @PostMapping("/addNewMovie")
    public ResponseEntity<?> addNewMovie(@RequestBody Movie movie) {
        if (repo.existsByName(movie.getName())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already exists");
        }
        else{
            repo.save(movie);
            return ResponseEntity.ok("Succesfully");
        }
    }
    @GetMapping("/getMovieData")
    public ResponseEntity<?> getMovieData(@RequestParam Long id){
        if (repo.existsById(id)){
            Movie currmovie = repo.getMovieById(id);
            return ResponseEntity.ok(currmovie);
        }
        else{
            return ResponseEntity.badRequest().body("Not found");
        }
    }
    @GetMapping("/findMovies")
    public ResponseEntity<?> findMovies(@RequestParam String name){
        if (repo.existsByNameContains(name)){
            return ResponseEntity.ok(repo.findAllByNameContains(name));
        }
        else{
            return ResponseEntity.badRequest().body("Not found");
        }
    }
    @DeleteMapping("/deleteMovie")
    public ResponseEntity<String> deleteMovie(@RequestParam Long id){
        try{
            if (repo.existsById(id)){
                repo.deleteById(id);
                return ResponseEntity.ok("Successfully deleted:"+id);
            }
            else{
                return ResponseEntity.badRequest().body("Movie not found");
            }
        } catch (Exception ex){
            throw ex;
        }
    }
}
