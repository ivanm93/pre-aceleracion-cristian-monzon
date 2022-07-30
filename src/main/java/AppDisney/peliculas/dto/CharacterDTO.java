package AppDisney.peliculas.dto;

import AppDisney.peliculas.entity.MovieEntity;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDTO {

    private Long id;
    private String image;
    @NotNull
    private String name;
    private Long age;
    private float weight;
    private String history;
    private List<MovieEntity> movies;

}
