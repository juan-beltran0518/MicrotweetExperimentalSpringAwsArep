package arep.edu.co.microservicios.repository;

import arep.edu.co.microservicios.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByUser_IdOrderByCreatedAtDesc(Long userId, Pageable pageable);
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable); // stream global
}
