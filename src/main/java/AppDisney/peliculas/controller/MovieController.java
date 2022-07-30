package AppDisney.peliculas.controller;

import AppDisney.peliculas.dto.MovieBasicDTO;
import AppDisney.peliculas.dto.MovieDTO;
import AppDisney.peliculas.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO movieDTO) throws Exception {
        MovieDTO newMovie = movieService.save(movieDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(newMovie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        MovieDTO movie = movieService.getMovieDTOById(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getDetailByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long gender,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<MovieBasicDTO> movies = movieService.getByFilters(name, gender, order);
        return ResponseEntity.ok(movies);
    }


    @PostMapping("/{id}/characters/{characterId}")
    public ResponseEntity<MovieDTO> addCharacter(@Valid @PathVariable Long id, @PathVariable Long characterId) {
        MovieDTO movieDTO = movieService.addCharacter(id, characterId);

        return ResponseEntity.ok().body(movieDTO);
    }

    @DeleteMapping("/{id}/characters/{characterId}")
    public ResponseEntity<MovieDTO> deleteCharacter(@PathVariable Long id, @PathVariable Long characterId) {
        MovieDTO movieDTO = movieService.removeCharacter(id, characterId);

        return ResponseEntity.ok().body(movieDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@Valid @RequestBody MovieDTO movieDTO, @PathVariable Long id) {
        MovieDTO result = movieService.updateMovie(id, movieDTO);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
