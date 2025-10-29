package arep.edu.co.microservicios.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity @Table(name = "post")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Post {
  @Id private UUID id;

  @Column(name = "user_id", nullable = false)
  private UUID userId;

  @Column(nullable = false, length = 140)
  private String contenido;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @PrePersist void pre() {
    if (id == null) id = UUID.randomUUID();
    if (createdAt == null) createdAt = Instant.now();
  }
}
