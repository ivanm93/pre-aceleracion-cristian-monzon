package AppDisney.peliculas.service.impl;

import AppDisney.peliculas.dto.GenderDTO;
import AppDisney.peliculas.entity.GenderEntity;
import AppDisney.peliculas.exception.ParamNotFound;
import AppDisney.peliculas.mapper.GenderMapper;
import AppDisney.peliculas.repository.GenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class GenderServiceImpl {

    @Autowired
    private GenderMapper genderMapper;
    @Autowired
    private GenderRepository genderRepository;

    public GenderDTO save(GenderDTO genderDTO){
        GenderEntity genderEntity = genderMapper.genderDTO2Entity(genderDTO);
        GenderEntity newGender = genderRepository.save(genderEntity);
        GenderDTO result = genderMapper.genderEntity2DTO(newGender);

        return result;
    }

    public  GenderDTO getDetailsById(Long id) {
        Optional<GenderEntity> genderEntityOptional = genderRepository.findById(id);

        if (genderEntityOptional.isPresent()) {
            GenderDTO genderDTO = genderMapper.genderEntity2DTO(genderEntityOptional.get());

            return genderDTO;
        }
        return null;
    }

    public GenderDTO update(GenderDTO genderDTO) {
        Optional<GenderEntity> gender = genderRepository.findById(genderDTO.getId());
        if(!gender.isPresent())
        {
            throw new ParamNotFound("ID de genero para modificar no encontrado");
        }
        genderMapper.modifyGenderRefreshValues(gender.get(), genderDTO);
        GenderEntity savedEntity = genderRepository.save(gender.get());
        GenderDTO result = genderMapper.genderEntity2DTO(savedEntity);
        return result;
    }
}
