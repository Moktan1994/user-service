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

    public UserDTO createUser(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        User saved = userRepository.save(user);
        return toDTO(saved);

    }
    private UserDTO toDTO(User user) {
        return new UserDTO(user.getName(),user.getEmail(),user.getRole());
    }


    public List<UserDTO> getAllUser() {
      return userRepository.findAll()
              .stream()
              .map(this::toDTO)
              .collect(Collectors.toList());
    }

    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return toDTO(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }
}
