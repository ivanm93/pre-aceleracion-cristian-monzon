package AppDisney.peliculas.service;

import AppDisney.peliculas.dto.MovieBasicDTO;
import AppDisney.peliculas.dto.MovieDTO;
import AppDisney.peliculas.entity.MovieEntity;

import java.util.List;


public interface MovieService {

    MovieDTO save(MovieDTO dto);

    MovieEntity getMovieById(Long id);

    List<MovieBasicDTO> getByFilters(String name, Long genderId, String order);

    MovieDTO getMovieDTOById(Long id);

    MovieDTO updateMovie(Long id, MovieDTO movieDTO);

    void delete(Long id);

    MovieDTO addCharacter(Long movieId, Long characterId);

    MovieDTO removeCharacter(Long movieId, Long characterId);
}
