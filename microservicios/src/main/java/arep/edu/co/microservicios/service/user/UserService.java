package arep.edu.co.microservicios.service.user;

import arep.edu.co.microservicios.model.User;
import arep.edu.co.microservicios.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

    public User create(String username, String email, String password) {
        var u = new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);
        return users.save(u);
    }

    public User get(Long id) {
        return users.findById(id).orElseThrow();
    }
}
