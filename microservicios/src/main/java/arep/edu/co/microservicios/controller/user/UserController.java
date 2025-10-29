package arep.edu.co.microservicios.controller.user;

import arep.edu.co.microservicios.model.User;
import arep.edu.co.microservicios.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse create(@Valid @RequestBody CrearUsuarioRequest req) {
        User u = service.create(req.username(), req.email(), req.password());
        return new UsuarioResponse(u.getId(), u.getUsername(), u.getEmail());
    }

    @GetMapping("/{id}")
    public UsuarioResponse get(@PathVariable Long id) {
        var u = service.get(id);
        return new UsuarioResponse(u.getId(), u.getUsername(), u.getEmail());
    }
}
