package com.rodrigo.alcoba.services.interfaces;

import com.rodrigo.alcoba.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> allUsers();

    Optional<User> findUserById(Integer id);

    User saveUser(User user);

    Optional<User> deactivateUser(Integer id);

    Optional<User> activateUser(Integer id);

    Optional<User> updateUser(User user, Integer id);

    List<User> filterUsers(String search, String userType, String state);
}
