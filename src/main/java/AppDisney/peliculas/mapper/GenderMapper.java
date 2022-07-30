package AppDisney.peliculas.mapper;

import AppDisney.peliculas.entity.GenderEntity;
import AppDisney.peliculas.dto.GenderDTO;
import org.springframework.stereotype.Component;

@Component
public class GenderMapper {

    public GenderEntity genderDTO2Entity(GenderDTO genderDTO) {
        GenderEntity genderEntity = new GenderEntity();
        genderEntity.setImage(genderDTO.getImage());
        genderEntity.setName(genderDTO.getName());

        return genderEntity;
    }

    public GenderDTO genderEntity2DTO(GenderEntity genderEntity) {
        GenderDTO genderDTO = new GenderDTO();
        genderDTO.setId(genderEntity.getId());
        genderDTO.setImage(genderEntity.getImage());
        genderDTO.setName(genderEntity.getName());

        return genderDTO;
    }

    public void modifyGenderRefreshValues(GenderEntity entity, GenderDTO genderDTO) {
        entity.setImage(genderDTO.getImage());
        entity.setName(genderDTO.getName());

    }
}
