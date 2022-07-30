package AppDisney.peliculas.service.impl;

import AppDisney.peliculas.dto.MovieBasicDTO;
import AppDisney.peliculas.dto.MovieDTO;
import AppDisney.peliculas.dto.MovieFiltersDTO;
import AppDisney.peliculas.entity.CharacterEntity;
import AppDisney.peliculas.entity.MovieEntity;
import AppDisney.peliculas.exception.ParamNotFound;
import AppDisney.peliculas.mapper.MovieMapper;
import AppDisney.peliculas.repository.CharacterRepository;
import AppDisney.peliculas.repository.MovieRepository;
import AppDisney.peliculas.repository.Specification.MovieSpecification;
import AppDisney.peliculas.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
 public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieServiceImpl MovieServiceImpl;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterServiceImpl CharacterServiceImpl;

    @Autowired
    private MovieSpecification movieSpecification;

    @Autowired
    private CharacterRepository characterRepository;

    private MovieEntity getMovieEntityById(Long id) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(id);
        if(movieEntity.isEmpty()){
            throw new ParamNotFound("Película con ID: " + id + " no encontrado");
        }
        return movieEntity.get();
    }

    private CharacterEntity getCharacterEntityById(Long id) {
        Optional<CharacterEntity> characterEntity = characterRepository.findById(id);
        if(characterEntity.isEmpty()){
            throw new ParamNotFound("Personaje con ID: " + id + " no encontrado");
        }
        return characterEntity.get();
    }


    public MovieEntity getMovieById(Long id) {
        Optional<MovieEntity> movieEntityOptional = movieRepository.findById(id);
        if(!movieEntityOptional.isPresent()){
            throw new ParamNotFound("ID de película no encontrado");
        }
        return movieEntityOptional.get();
    }

    public MovieDTO getMovieDTOById(Long id){
        Optional<MovieEntity> movieEntity = movieRepository.findById(id);
        if(!movieEntity.isPresent()) {
            throw new ParamNotFound("ID de película no encontrado");
        }
        MovieDTO movieDTO = movieMapper.movieEntity2DTO(movieEntity.get(), true);
        return movieDTO;
    }

    public List<MovieBasicDTO> getByFilters(String name, Long genderId, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, genderId, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        List<MovieBasicDTO> dtos = movieMapper.movieEntityList2BasicDTOList(entities,true);
        return dtos;
    }

    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto, true);
        MovieEntity savedEntity = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(savedEntity, true);
        return result;
    }

    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        Optional<MovieEntity> entity = movieRepository.findById(id);

        if(!entity.isPresent()) {
            throw new ParamNotFound("ID de título para modificar no encontrado");
        }
        this.movieMapper.modifyMovieRefreshValues(entity.get(),movieDTO);
        MovieEntity savedEntity = movieRepository.save(entity.get());
        MovieDTO result = movieMapper.movieEntity2DTO(savedEntity, true);
        return result;
    }

    public MovieDTO addCharacter(Long movieId, Long characterId) {
        MovieEntity movieEntity = getMovieEntityById(movieId);
        CharacterEntity characterEntity = getCharacterEntityById(characterId);
        movieEntity.addCharacter(characterEntity);
        return movieMapper.movieEntity2DTO(movieEntity, true);
    }

    public MovieDTO removeCharacter(Long movieId, Long characterId) {
      Optional<MovieEntity> movie = this.movieRepository.findById(movieId);

      if (!movie.isPresent()){
          throw new ParamNotFound("Pelicula no encontrada");
      }

        MovieEntity movieEntity = getMovieEntityById(movieId);
        CharacterEntity characterEntity = getCharacterEntityById(characterId);
        movieEntity.removeCharacter(characterEntity);
        return movieMapper.movieEntity2DTO(movieEntity, true);
    }

    public void delete(Long id) {
        Optional<MovieEntity> movieEntityOptional = movieRepository.findById(id);
        if(!movieEntityOptional.isPresent())
        {
            throw new ParamNotFound("ID de título no encontrado, no se pudo eliminar");
        }
        movieRepository.deleteById(id);
    }
}
