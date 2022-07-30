package AppDisney.peliculas.service;

import AppDisney.peliculas.dto.CharacterBasicDTO;
import AppDisney.peliculas.dto.CharacterDTO;
import AppDisney.peliculas.entity.CharacterEntity;

import java.util.List;

public interface CharacterService {

    CharacterDTO save(CharacterDTO characterDTO, Long movieId);

    CharacterEntity getCharacterById(Long id);

    CharacterDTO getCharacterDTOById(Long id);

    List<CharacterDTO> getCharacters();

    List<CharacterBasicDTO> getAllCharactersBasic();

    CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO);

    List<CharacterDTO> getByFilters(String name, Integer age, float weight, List<Long> movies);

    void delete(Long id);
}