package AppDisney.peliculas.dto;

import AppDisney.peliculas.entity.CharacterEntity;
import AppDisney.peliculas.entity.GenderEntity;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Long id;
    private String image;
    @NotNull
    private String title;
    @Pattern(regexp= "\\d{4}-\\d{2}-\\d{2}" , message = "El formato de fecha debe ser yyyy-MM-dd")
    private LocalDate creationDate;
    @Min(1)
    @Max(5)
    private int rating;
    private GenderEntity gender;
    private Long genderId;
    private List<CharacterEntity> characters;

}
