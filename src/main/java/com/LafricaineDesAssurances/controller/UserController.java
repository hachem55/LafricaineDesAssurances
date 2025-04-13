package com.LafricaineDesAssurances.controller;
import com.LafricaineDesAssurances.model.user;
import com.LafricaineDesAssurances.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")  // Allow calls from Angular (change if using a different port)

public class UserController {

    @Autowired
    private UserRepository userRepository;

    // GET all users
    @GetMapping
    public List<user> getAllUsers() {
        return userRepository.findAll();
    }

    // GET user by ID
    @GetMapping("/{id}")
    public user getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);  // Return null if not found
    }

    // POST new user
    @PostMapping
    public user createUser(@RequestBody user user) {
        return userRepository.save(user);
    }

    // PUT update user
    @PutMapping("/{id}")
    public user updateUser(@PathVariable Long id, @RequestBody user user) {
        user.setId(id);
        return userRepository.save(user);
    }

    // DELETE user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
