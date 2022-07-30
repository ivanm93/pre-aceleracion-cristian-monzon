package AppDisney.peliculas.dto;

import AppDisney.peliculas.entity.MovieEntity;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenderDTO {

    private Long id;
    @NotNull
    private String name;
    private String image;
    private Set<MovieEntity> movies;


}
