package arep.edu.co.microservicios.controller.user;

import java.util.UUID;
import java.time.Instant;

public record UsuarioResponse(UUID id, String username, String displayName, Instant createdAt) {}
