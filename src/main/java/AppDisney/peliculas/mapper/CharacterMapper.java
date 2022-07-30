package AppDisney.peliculas.mapper;

import AppDisney.peliculas.entity.CharacterEntity;
import AppDisney.peliculas.dto.CharacterBasicDTO;
import AppDisney.peliculas.dto.CharacterDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {



    public CharacterEntity characterDTO2Entity (CharacterDTO dto) {

        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setHistory(dto.getHistory());
        characterEntity.setMovies(dto.getMovies());

        return characterEntity;
    }
    public CharacterDTO characterEntity2DTO(CharacterEntity characterEntity, boolean loadMovies) {

        CharacterDTO dto = new CharacterDTO();
        dto.setId(characterEntity.getId());
        dto.setImage(characterEntity.getImage());
        dto.setName(characterEntity.getName());
        dto.setAge(characterEntity.getAge());
        dto.setWeight(characterEntity.getWeight());
        dto.setHistory(characterEntity.getHistory());
        dto.setMovies(characterEntity.getMovies());

        return dto;
    }

    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> characterEntities, boolean loadMovies) {

        List<CharacterDTO> dtos = new ArrayList<>();

        for (CharacterEntity entity : characterEntities) {
            dtos.add(this.characterEntity2DTO(entity, loadMovies));
        }

        return dtos;
    }

    public List<CharacterBasicDTO> characterEntityList2BasicDTOList(List<CharacterEntity> characterEntities) {

        List<CharacterBasicDTO> dtos = new ArrayList<>();
        CharacterBasicDTO basicDTO;

        for (CharacterEntity entity : characterEntities) {
            basicDTO = new CharacterBasicDTO();
            basicDTO.setImage(entity.getImage());
            basicDTO.setName(entity.getName());
            dtos.add(basicDTO);
        }

        return dtos;
    }

    public List<CharacterEntity> characterDTOList2EntityList(List<CharacterDTO> characterDTOS) {
        List<CharacterEntity> characterEntities = new ArrayList<>();

        for (CharacterDTO dto : characterDTOS) {
            characterEntities.add(this.characterDTO2Entity(dto));
        }
        return characterEntities;
    }



    public void modifyCharacterRefreshValues(CharacterEntity entity, CharacterDTO characterDTO) {
        entity.setImage(characterDTO.getImage());
        entity.setName(characterDTO.getName());
        entity.setAge(characterDTO.getAge());
        entity.setWeight(characterDTO.getWeight());
        entity.setHistory(characterDTO.getHistory());
    }
}
