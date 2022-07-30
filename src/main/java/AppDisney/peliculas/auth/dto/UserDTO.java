package AppDisney.peliculas.auth.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    @Email(message = "El nombre de usuario debe ser un correo electrónico")
    private String username;

    @Size(min = 8)
    private String password;
}
