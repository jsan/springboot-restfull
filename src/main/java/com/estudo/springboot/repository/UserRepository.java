package com.estudo.springboot.repository;

import com.estudo.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByEmail(String email);
    List<User> findByFirstNameAndLastName(String name, String lastName);
    @Query("select u from User u where u.firstName like CONCAT('%',:firstName ,'%')")
    List<User> findUsersByFirstname(@Param("firstName") String firstName);
}
