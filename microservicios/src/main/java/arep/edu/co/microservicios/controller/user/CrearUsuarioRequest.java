package arep.edu.co.microservicios.controller.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CrearUsuarioRequest(
    @NotBlank String username,
    @Email @NotBlank String email,
    @NotBlank String password
) {}
