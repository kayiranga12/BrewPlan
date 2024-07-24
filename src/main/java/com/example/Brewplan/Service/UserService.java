package com.example.Brewplan.Service;

import com.example.Brewplan.Model.User;
import com.example.Brewplan.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (!userRepository.findByUsername("kayiranga").isPresent()) {
            User admin = new User();
            admin.setUsername("kayiranga");
            admin.setEmail("kayiranga420@gmail.com");
            admin.setPassword("1234");
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public User authenticate(String email, String password) {
        List<User> users = userRepository.findAllByEmail(email);
        if (users.size() == 1 && users.get(0).getPassword().equals(password)) {
            return users.get(0);
        }
        return null;
    }

    public void updateUserRole(Long id, String role) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setRole(role);
        userRepository.save(user);
    }
}
