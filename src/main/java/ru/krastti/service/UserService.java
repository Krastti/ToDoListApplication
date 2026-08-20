package ru.krastti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.krastti.entity.User;
import ru.krastti.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(String username, String email, String password) {
        User user = new User(username, email, password);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Transactional
    public void updateUsername(Long id, String username) {
        User user = userRepository.getUserById(id);
        user.setUsername(username);
    }

    @Transactional
    public void updateEmail(Long id, String email) {
        User user = userRepository.getUserById(id);
        user.setEmail(email);
    }

    @Transactional
    public void updatePassword(Long id, String password) {
        User user = userRepository.getUserById(id);
        user.setPassword(password);
    }

    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}