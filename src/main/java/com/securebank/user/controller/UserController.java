package com.securebank.user.controller;

import com.securebank.user.dto.UserDTO;
import com.securebank.user.mapper.UserMapper;
import com.securebank.user.model.User;
import com.securebank.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO dto) {
        User user = mapper.toUser(dto);
        User saved = userService.createUser(user);
        return new ResponseEntity<>(mapper.toDTO(saved), HttpStatus.CREATED);

    }
    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAllUser().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        if(user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(user));
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);

    }
}
