package arep.edu.co.microservicios.controller.user;

import jakarta.validation.constraints.NotBlank;

public record CrearUsuarioRequest(@NotBlank String username, String displayName) {}
