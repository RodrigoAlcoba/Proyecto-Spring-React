package com.rodrigo.alcoba.repositories;

import com.rodrigo.alcoba.model.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE " +
            "(:name IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:lastname IS NULL OR LOWER(u.lastname) LIKE LOWER(CONCAT('%', :lastname, '%'))) " +
            "AND (:username IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%'))) " +
            "AND (:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))) " +
            "AND (:userType IS NULL OR LOWER(u.userType.name) LIKE LOWER(CONCAT('%', :userType, '%'))) " +
            "AND (:state IS NULL OR LOWER(u.state.name) LIKE LOWER(CONCAT('%', :state, '%')))")
    List<User> filterUsers(@Param("name") String name,
                           @Param("lastname") String lastname,
                           @Param("username") String username,
                           @Param("email") String email,
                           @Param("userType") String userType,
                           @Param("state") String state);

    boolean existsByUsername(String username);

}
