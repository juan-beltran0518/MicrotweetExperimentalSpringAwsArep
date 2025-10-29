package arep.edu.co.microservicios.controller.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CrearPostRequest(
    @NotBlank @Size(max = 140) String contenido
) {}
