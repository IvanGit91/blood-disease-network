package it.ivan.bdn.controller;

import it.ivan.bdn.exception.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import it.ivan.bdn.model.Hospital;
import it.ivan.bdn.model.User;
import it.ivan.bdn.repository.HospitalRepository;
import it.ivan.bdn.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Log
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody final User user) {
        User current_user = userRepository.findByUsername(user.getUsername());

        Hospital hosp = hospitalRepository.findByCode(user.getHospitalCode());

        if (hosp == null) //check that there is a match between the entered code and the hospital structure specified during registration
        {
            throw new AppException(HttpStatus.CONFLICT,
                    "Unable to register user. The specified code "
                            + user.getHospitalCode() + " does not match " + user.getHospitalName());
        }
        if (current_user == null) //check that no user exists with the specified username
        {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            throw new AppException(HttpStatus.CONFLICT,
                    "Unable to register user. A user with username "
                            + user.getUsername() + " already exists.");
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody final User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable("id") final Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new AppException(HttpStatus.NOT_FOUND,
                    "User not found with code: " + id);
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
