package pl.Pakinio.ProjectTIN.service;

import org.springframework.stereotype.Service;
import pl.Pakinio.ProjectTIN.exception.NotFoundIdException;
import pl.Pakinio.ProjectTIN.model.Player;
import pl.Pakinio.ProjectTIN.model.User;
import pl.Pakinio.ProjectTIN.repository.UserRepository;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUser(String name) throws NotFoundIdException {
        return userRepository.findByUsername(name).
                orElseThrow(() -> new NotFoundIdException("not found"));
    }
}
