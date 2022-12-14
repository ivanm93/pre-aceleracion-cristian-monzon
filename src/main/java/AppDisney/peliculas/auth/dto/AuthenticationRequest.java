package AppDisney.peliculas.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthenticationRequest {

    @Email(message = "El nombre de usuario debe ser un correo electrónico")
    private String username;

    @Size(min = 8)
    private String password;
}
