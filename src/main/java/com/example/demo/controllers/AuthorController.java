package com.example.demo.controllers;

import com.example.demo.classes.Author;
import com.example.demo.classes.Movie;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    AuthorRepository repo;
    MovieRepository repomovie;
    public AuthorController(AuthorRepository repo,MovieRepository repomovie){
        this.repo = repo;
        this.repomovie = repomovie;
    }
    @CrossOrigin
    @GetMapping("/getAllAuthors")
    public List<Author> getAuthors(){
        return repo.findAll();
    }
    @CrossOrigin
    @PostMapping("/addAuthor")
    public void AddAuthor(@RequestBody Author author, @RequestParam(name = "movie-id") Long movieId){
        Movie currmovie = repomovie.findById(movieId).orElseThrow();
        currmovie.getAuthors().add(author);
        repomovie.save(currmovie);
    }

}
