package arep.edu.co.microservicios.controller.post;

import arep.edu.co.microservicios.service.post.PostService;
import arep.edu.co.microservicios.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
  private final PostService service;

  // Crea post (requiere JWT). extrae userId del JWT (adjust claim key).
  @PostMapping("/posts")
  @ResponseStatus(HttpStatus.CREATED)
  public PostResponse create(@Validated @RequestBody CrearPostRequest req,
                             @AuthenticationPrincipal Jwt jwt) {
    // Ajusta el claim según IdP: por ejemplo "sub" o "custom:user_id"
    UUID userId = UUID.fromString(jwt.getClaimAsString("sub"));
    Post p = service.create(userId, req.contenido());
    return new PostResponse(p.getId(), p.getUserId(), p.getContenido(), p.getCreatedAt());
  }

  // Stream global
  @GetMapping("/stream")
  public Page<PostResponse> stream(Pageable pageable) {
    return service.stream(pageable)
      .map(p -> new PostResponse(p.getId(), p.getUserId(), p.getContenido(), p.getCreatedAt()));
  }

  // Posts por usuario
  @GetMapping("/posts")
  public Page<PostResponse> byUser(@RequestParam(required = false) UUID authorId,
                                   Pageable pageable) {
    var page = (authorId == null) ? service.stream(pageable) : service.byUser(authorId, pageable);
    return page.map(p -> new PostResponse(p.getId(), p.getUserId(), p.getContenido(), p.getCreatedAt()));
  }
}
