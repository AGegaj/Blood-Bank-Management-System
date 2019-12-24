package org.fiek.bloodmanagementsystem.repository;

import org.fiek.bloodmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByPersonalNumber(String personalNumber);

    @Query("SELECT u FROM User u WHERE u.role.name = :role")
    Optional<List<User>> findAllByRole(@Param("role") String role);
}
