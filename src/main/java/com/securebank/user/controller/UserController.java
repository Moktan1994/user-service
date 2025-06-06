package com.securebank.user.controller;

import com.securebank.user.dto.UserDTO;
import com.securebank.user.model.User;
import com.securebank.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        UserDTO saved = userService.createUser(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> dtos = userService.getAllUser();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return new ResponseEntity(userService.getUser(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully",HttpStatus.OK);

    }
}
