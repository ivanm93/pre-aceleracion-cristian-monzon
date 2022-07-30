package AppDisney.peliculas.controller;

import AppDisney.peliculas.dto.CharacterBasicDTO;
import AppDisney.peliculas.dto.CharacterDTO;
import AppDisney.peliculas.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping
    public ResponseEntity<CharacterDTO> save (@RequestBody CharacterDTO characterDTO, @RequestParam Long movieId){
        CharacterDTO result = characterService.save (characterDTO, movieId);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO>getById(@PathVariable Long id ){
      CharacterDTO characterDTO = characterService.getCharacterDTOById(id);

      return ResponseEntity.ok().body(characterDTO);
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> search(@RequestParam(required = false) String name,
                                                          @RequestParam(required = false) Integer age,
                                                          @RequestParam(required = false) float weight,
                                                          @RequestParam(required = false) List<Long> movies) {
        List<CharacterDTO> characters = characterService.getByFilters(name, age, weight, movies);
        return ResponseEntity.ok().body(characters);
    }
    @PostMapping("/{id}")
    public ResponseEntity<CharacterDTO> update (@PathVariable Long id, @RequestBody CharacterDTO characterDTO) {
        CharacterDTO result = characterService.updateCharacter(id, characterDTO);

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
