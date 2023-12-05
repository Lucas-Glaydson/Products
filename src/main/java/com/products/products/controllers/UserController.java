package com.products.products.controllers;

import com.products.products.dtos.UserDTO;
import com.products.products.models.Users;
import com.products.products.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    UserRepository userRepository;

    @PostMapping
    private ResponseEntity<Users> registerUser(@RequestBody UserDTO userDTO){
        var user = new Users();
        BeanUtils.copyProperties(userDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @GetMapping("/{id}")
    private ResponseEntity<Object> getUser(@PathVariable(value = "id") UUID id){
        Optional<Users> user = userRepository.findById(id);

        return user.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found") : ResponseEntity.status(HttpStatus.OK).body(user.get());
    }
}
