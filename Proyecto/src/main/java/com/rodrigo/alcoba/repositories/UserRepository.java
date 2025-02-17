package com.rodrigo.alcoba.repositories;

import com.rodrigo.alcoba.model.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE " +
            "(:search IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(u.lastname) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND (:userType IS NULL OR u.userType.id = :userType) " +
            "AND (:state IS NULL OR u.state.id = :state)")
    List<User> filterUsers(@Param("search") String search,
                           @Param("userType") Integer userType,
                           @Param("state") Integer state);





    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);



}
