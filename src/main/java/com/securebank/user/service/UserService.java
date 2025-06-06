package com.securebank.user.service;

import com.securebank.user.dto.UserDTO;
import com.securebank.user.model.User;
import com.securebank.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   public User createUser(User user) {
        return userRepository.save(user);
   }
   public List<User> getAllUser() {
        return userRepository.findAll();
   }
   public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
   }
   public void deleteById(Long id) {
        userRepository.deleteById(id);
   }
}
