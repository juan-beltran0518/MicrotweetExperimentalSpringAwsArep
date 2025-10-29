package arep.edu.co.microservicios.controller.user;

import arep.edu.co.microservicios.service.user.UserService;
import arep.edu.co.microservicios.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UsuarioResponse create(@RequestBody CrearUsuarioRequest req) {
    User u = service.create(req.username(), req.displayName());
    return new UsuarioResponse(u.getId(), u.getUsername(), u.getDisplayName(), u.getCreatedAt());
  }

  @GetMapping("/{id}")
  public UsuarioResponse get(@PathVariable UUID id) {
    var u = service.get(id);
    return new UsuarioResponse(u.getId(), u.getUsername(), u.getDisplayName(), u.getCreatedAt());
  }
}
