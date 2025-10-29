package arep.edu.co.microservicios.service.post;

import arep.edu.co.microservicios.model.Post;
import arep.edu.co.microservicios.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service @RequiredArgsConstructor
public class PostService {
  private final PostRepository repo;

  public Post create(UUID userId, String contenido) {
    var p = Post.builder().userId(userId).contenido(contenido).build();
    return repo.save(p);
  }

  public Page<Post> stream(Pageable pageable) {
    return repo.findAllByOrderByCreatedAtDesc(pageable);
  }

  public Page<Post> byUser(UUID userId, Pageable pageable) {
    return repo.findAllByUserIdOrderByCreatedAtDesc(userId, pageable);
  }
}
