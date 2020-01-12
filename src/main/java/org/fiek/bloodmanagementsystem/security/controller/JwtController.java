package org.fiek.bloodmanagementsystem.security.controller;

import org.fiek.bloodmanagementsystem.entity.User;
import org.fiek.bloodmanagementsystem.repository.UserRepository;
import org.fiek.bloodmanagementsystem.security.component.JwtTokenComponent;
import org.fiek.bloodmanagementsystem.security.model.ApplicationUser;
import org.fiek.bloodmanagementsystem.security.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("bloodmanagement/security")
public class JwtController {

    @Autowired
    private JwtTokenComponent jwtTokenComponent;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/auth")
    public ResponseEntity<?> auth(@RequestBody ApplicationUser applicationUser) {
        Optional<User> user = userRepository.findByUsernameOrEmail(applicationUser.getUsername());
        if (user.isPresent()) {
            if (!passwordMatches(applicationUser.getPassword(), user.get().getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.ok(new Token(jwtTokenComponent.generateToken(user.get())));
        }else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private Boolean passwordMatches(String rawPassword, String encodedPassword) {
        PasswordEncoder passwordEnocder = new BCryptPasswordEncoder();
        if (passwordEnocder.matches(rawPassword, encodedPassword)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
