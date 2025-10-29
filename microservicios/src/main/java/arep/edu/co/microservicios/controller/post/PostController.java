package arep.edu.co.microservicios.controller.post;

import arep.edu.co.microservicios.model.Post;
import arep.edu.co.microservicios.model.User;
import arep.edu.co.microservicios.service.post.PostService;
import arep.edu.co.microservicios.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse create(@Valid @RequestBody CrearPostRequest req,
                               @AuthenticationPrincipal Jwt jwt) {

        String email = jwt.getClaimAsString("email");
        String username = jwt.getClaimAsString("cognito:username");

        if (email == null && username == null) {

            throw new IllegalStateException("El token JWT no contiene 'email' ni 'cognito:username'. Revisa scopes en Cognito.");
        }

        User u = (email != null)
                 ? userService.getOrCreateByEmail(email, username)
                 : userService.getOrCreateByEmail(username + "@local", username); 
        Long streamId = (req.streamId() != null) ? req.streamId() : 1L;

        Post p = postService.create(u.getId(), streamId, req.content());
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
        return postService.stream(pageable)
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
        return postService.byUser(authorId, pageable)
                .map(p -> new PostResponse(
                        p.getId(),
                        p.getUser().getId(),
                        p.getStream().getId(),
                        p.getContent(),
                        p.getCreatedAt()
                ));
    }
}
