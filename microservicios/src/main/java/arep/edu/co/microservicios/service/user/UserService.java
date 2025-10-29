package arep.edu.co.microservicios.service.user;

import arep.edu.co.microservicios.model.User;
import arep.edu.co.microservicios.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service @RequiredArgsConstructor
public class UserService {
  private final UserRepository repo;

  public User create(String username, String displayName) {
    var u = User.builder().username(username).displayName(displayName).build();
    return repo.save(u);
  }

  public User get(UUID id) { return repo.findById(id).orElseThrow(); }
}
