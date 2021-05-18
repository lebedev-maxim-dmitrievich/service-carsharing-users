package ru.lebedev.servicecarsharingusers.security;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.lebedev.servicecarsharingusers.exception.UserNotFoundException;
import ru.lebedev.servicecarsharingusers.model.User;
import ru.lebedev.servicecarsharingusers.repository.UserRepository;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }

        User user = userOptional.get();

        return new UserDetailsImpl(user);
    }
}
