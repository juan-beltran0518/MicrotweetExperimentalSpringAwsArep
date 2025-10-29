package arep.edu.co.microservicios.controller.post;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CrearPostRequest(
    @NotNull Long userId,
    Long streamId, // opcional; si null, usaremos 1L (Global Stream)
    @NotBlank @Size(max = 140) String content
) {}
