package arep.edu.co.microservicios.controller.post;

import java.util.UUID;
import java.time.Instant;

public record PostResponse(
    UUID id,
    UUID autorId,
    String contenido,
    Instant createdAt
) {}
