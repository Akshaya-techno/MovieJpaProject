/*
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here

package com.example.movie.service;

import com.example.movie.model.Movie;
import com.example.movie.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

// Do not modify the below code
@Service
public class MovieJpaService implements MovieRepository {
    @Autowired
    private MovieJpaRepository movieJpaRepository;

    @Override
    public List<Movie> MovieList() {
        List<Movie> movies =  movieJpaRepository.findAll();
        return movies;
    }

    @Override
    public Movie addMovie(Movie movie) {
        Movie savedMovie = movieJpaRepository.save(movie);
        return savedMovie;
    }

    @Override
    public Movie getMovieById(int movieId) {
        try {
            Movie movie = movieJpaRepository.findById(movieId).get();
            return movie;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Movie updateMovie(int movieId, Movie movie) {
        try {
            Movie toUpdate = movieJpaRepository.findById(movieId).get();

            if (movie.getMovieName() != null) {
                toUpdate.setMovieName(movie.getMovieName());
            }
            if (movie.getLeadActor() != null) {
                toUpdate.setLeadActor(movie.getLeadActor());
            }
            movieJpaRepository.save(toUpdate);
            return toUpdate;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteMovie(int movieId) {
        try {
            movieJpaRepository.deleteById(movieId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
}
