package arep.edu.co.microservicios.repository;

import arep.edu.co.microservicios.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
  Page<Post> findAllByUserIdOrderByCreatedAtDesc(UUID userId, Pageable pageable);
  Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable); 
}
