package pl.Pakinio.ProjectTIN.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.Pakinio.ProjectTIN.model.User;
import pl.Pakinio.ProjectTIN.repository.UserRepository;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public JpaUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(s);

        if (!userOptional.isPresent()){
            throw new UsernameNotFoundException("No user found with username");
        }

        User user = userOptional.get();

        return user;

    }
}
