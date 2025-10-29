package arep.edu.co.microservicios.controller.post;

import java.time.LocalDateTime;

public record PostResponse(
    Long id,
    Long userId,
    Long streamId,
    String content,
    LocalDateTime createdAt
) {}
