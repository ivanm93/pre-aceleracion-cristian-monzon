package AppDisney.peliculas.service.impl;

import AppDisney.peliculas.dto.CharacterBasicDTO;
import AppDisney.peliculas.dto.CharacterDTO;
import AppDisney.peliculas.dto.CharacterFiltersDTO;
import AppDisney.peliculas.entity.CharacterEntity;
import AppDisney.peliculas.exception.ParamNotFound;
import AppDisney.peliculas.mapper.CharacterMapper;
import AppDisney.peliculas.repository.CharacterRepository;
import AppDisney.peliculas.repository.Specification.CharacterSpecification;
import AppDisney.peliculas.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private MovieServiceImpl movieServiceImpl;

    @Autowired
    private CharacterSpecification characterSpecification;

    public CharacterEntity getCharacterById(Long id) {
        Optional<CharacterEntity> characterEntity = characterRepository.findById(id);
        if(!characterEntity.isPresent()) {
            throw new ParamNotFound("ID de personaje no encontrado");
        }
        return characterEntity.get();
    }

    public CharacterDTO getCharacterDTOById(Long id){
        Optional<CharacterEntity> characterEntity = characterRepository.findById(id);
        if(!characterEntity.isPresent()) {
            throw new ParamNotFound("ID de personaje no encontrado");
        }
        CharacterDTO characterDTO = characterMapper.characterEntity2DTO(characterEntity.get(),true);
        return characterDTO;
    }

    public List<CharacterDTO> getCharacters() {
        List<CharacterEntity> characterEntities = (List<CharacterEntity>) characterRepository.findAll();
        List<CharacterDTO> result = characterMapper.characterEntityList2DTOList(characterEntities,true);
        return result;
    }

    public List<CharacterBasicDTO> getAllCharactersBasic() {
        List<CharacterEntity> characterEntitiesBasicList = (List<CharacterEntity>) characterRepository.findAll();
        return characterMapper.characterEntityList2BasicDTOList(characterEntitiesBasicList);
    }


    public CharacterDTO save(CharacterDTO dto, Long movieId) {
        CharacterEntity characterEntity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity savedEntity = characterRepository.save(characterEntity);
        movieServiceImpl.addCharacter(movieId, savedEntity.getId());
        CharacterDTO result = characterMapper.characterEntity2DTO(savedEntity,true);
        return result;
    }

    public CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO) {
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if(!entity.isPresent())
        {
            throw new ParamNotFound("ID de personaje para modificar no encontrado");
        }
        characterMapper.modifyCharacterRefreshValues(entity.get(),characterDTO);
        CharacterEntity savedEntity = characterRepository.save(entity.get());
        CharacterDTO result = characterMapper.characterEntity2DTO(savedEntity, true);
        return result;
    }


    public List<CharacterDTO> getByFilters(String name, Integer age, float weight, List<Long> movies) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, movies);
        List<CharacterEntity> characterEntities = (List<CharacterEntity>) characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        List<CharacterDTO> characterDTO = characterMapper.characterEntityList2DTOList(characterEntities,true);
        return characterDTO;
    }

    public void delete(Long id) {
        Optional<CharacterEntity>characterEntity = characterRepository.findById(id);
        if(!characterEntity.isPresent())
        {
            throw new ParamNotFound("ID de personaje no encontrado, no se pudo eliminar");
        }
        characterRepository.deleteById(id);
    }
}
