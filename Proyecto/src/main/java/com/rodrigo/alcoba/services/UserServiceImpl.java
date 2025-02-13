package com.rodrigo.alcoba.services;

import com.rodrigo.alcoba.model.entities.User;
import com.rodrigo.alcoba.repositories.StateRepository;
import com.rodrigo.alcoba.repositories.UserRepository;
import com.rodrigo.alcoba.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    StateRepository stateRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> allUsers() {
        return (List<User>) userRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }


    @Override
    @Transactional
    public User saveUser(User user) {

        if (user.getState() == null) {
            user.setState(stateRepository.findById(3).orElseThrow());
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            user.setUsername((user.getName() + "." + user.getLastname()).toLowerCase());
        }

        user.setUsername(generateUniqueUsername(user.getUsername()));

        return userRepository.save(user);

    }
    private String generateUniqueUsername(String username) {
        int counter = 1;
        String  usernameFinal = username;
        while (userRepository.existsByUsername(usernameFinal)) {
            usernameFinal = username + counter;
            counter++;
        }
        return usernameFinal;
    }

    @Override
    @Transactional
    public Optional<User> deactivateUser(Integer id) {
        Optional<User> userOptional = this.findUserById(id);
        User finalUser = null;

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setState(stateRepository.findById(2).orElseThrow());
            finalUser = userRepository.save(user);
        }

        return Optional.ofNullable(finalUser);
    }

    @Override
    @Transactional
    public Optional<User> activateUser(Integer id) {
        Optional<User> userOptional = this.findUserById(id);
        User finalUser = null;

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setState(stateRepository.findById(1).orElseThrow());
            finalUser = userRepository.save(user);
        }

        return Optional.ofNullable(finalUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> filterUsers(String name, String lastname, String username, String email, String userType, String state) {
        return userRepository.filterUsers(name, lastname, username, email, userType, state);
    }

    @Override
    @Transactional
    public Optional<User> updateUser(User user, Integer id) {
        Optional<User> userOptional = this.findUserById(id);
        User finalUser = null;
        if(userOptional.isPresent()){

            User userUpdated = userOptional.orElseThrow();
            userUpdated.setName(user.getName());
            userUpdated.setLastname(user.getLastname());
            userUpdated.setPhone(user.getPhone());
            userUpdated.setPassword(user.getPassword());
            userUpdated.setEmail(user.getEmail());
            userUpdated.setBirthdate(user.getBirthdate());

            finalUser = userRepository.save(userUpdated);

        }
        return Optional.ofNullable(finalUser);
    }

}
