package arep.edu.co.microservicios.controller.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CrearPostRequest(
    Long streamId,                 
    @NotBlank @Size(max = 140) String content
) {}
