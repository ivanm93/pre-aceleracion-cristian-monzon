package AppDisney.peliculas.service;

import AppDisney.peliculas.dto.GenderDTO;


public interface GenderService {
    GenderDTO save(GenderDTO dto);

    GenderDTO getDetailsById(Long id);

    GenderDTO update(GenderDTO genderDTO);


}
