package com.rodrigo.alcoba.controllers;


import com.rodrigo.alcoba.model.entities.User;
import com.rodrigo.alcoba.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> listUser(){
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id){
        Optional<User> userOptional = userService.findUserById(id);

        if(userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user){
        Optional<User> userOptional = userService.updateUser(user, id);
        if(userOptional.isPresent()){

            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> desactivateUser(@PathVariable Integer id){
        Optional<User> userOptional = userService.findUserById(id);
        if(userOptional.isPresent()){
            userService.deactivateUser(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activateUser(@PathVariable Integer id){
        Optional<User> userOptional = userService.findUserById(id);
        if(userOptional.isPresent()){
            userService.activateUser(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<User>> filterUsers(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String userType,
            @RequestParam(required = false) String state) {

        List<User> users = userService.filterUsers(search, userType, state);
        return ResponseEntity.ok(users);
    }



}
