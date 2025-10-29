package arep.edu.co.microservicios.controller.post;

import arep.edu.co.microservicios.model.Post;
import arep.edu.co.microservicios.service.post.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse create(@Valid @RequestBody CrearPostRequest req) {
        Post p = service.create(req.userId(), req.streamId(), req.content());
        return new PostResponse(
                p.getId(),
                p.getUser().getId(),
                p.getStream().getId(),
                p.getContent(),
                p.getCreatedAt()
        );
    }

    @GetMapping("/stream")
    public Page<PostResponse> stream(Pageable pageable) {
        return service.stream(pageable)
                .map(p -> new PostResponse(
                        p.getId(),
                        p.getUser().getId(),
                        p.getStream().getId(),
                        p.getContent(),
                        p.getCreatedAt()
                ));
    }

    @GetMapping("/posts")
    public Page<PostResponse> byUser(@RequestParam Long authorId, Pageable pageable) {
        return service.byUser(authorId, pageable)
                .map(p -> new PostResponse(
                        p.getId(),
                        p.getUser().getId(),
                        p.getStream().getId(),
                        p.getContent(),
                        p.getCreatedAt()
                ));
    }
}
