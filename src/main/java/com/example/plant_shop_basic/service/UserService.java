package com.example.plant_shop_basic.service;

import com.example.plant_shop_basic.entity.User;
import com.example.plant_shop_basic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get all users from the database
     * @return List of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get a user by ID
     * @param id The ID of the user
     * @return Optional containing the user if found, or empty if not found
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Create a new user
     * @param user The user to be created
     * @return The newly created user
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Update an existing user
     * @param id The ID of the user to update
     * @param user The updated user details
     * @return Optional containing the updated user if found, or empty if not found
     */
    public Optional<User> updateUser(Long id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            return userRepository.save(existingUser);
        });
    }

    /**
     * Delete a user by ID
     * @param id The ID of the user to delete
     * @return True if the user was deleted, false if not found
     */
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
