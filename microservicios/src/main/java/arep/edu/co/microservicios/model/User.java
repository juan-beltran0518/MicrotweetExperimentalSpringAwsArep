package arep.edu.co.microservicios.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity @Table(name = "usuario")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
  @Id private UUID id;
  @Column(nullable = false, unique = true, length = 50)
  private String username;
  @Column(name = "display_name", length = 100)
  private String displayName;
  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @PrePersist void pre() {
    if (id == null) id = UUID.randomUUID();
    if (createdAt == null) createdAt = Instant.now();
  }
}
