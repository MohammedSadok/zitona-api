package ena.api.zitona.controllers;

import ena.api.zitona.entitys.User;
import ena.api.zitona.response.ResponseData;
import ena.api.zitona.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @ResponseBody
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<User>> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseData<>(user, HttpStatus.OK, "User Details are given here"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseData<>(null, HttpStatus.OK, "User not found"));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseData<Optional<User>>> getUserById(@PathVariable String email) {
        Optional<User> user = userService.findUserByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseData<>(user, HttpStatus.OK, "User Details are given here"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseData<>(null, HttpStatus.OK, "User not found"));
    }

    @PostMapping
    public ResponseEntity<ResponseData<User>> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseData<>(savedUser, HttpStatus.CREATED, "User created successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseData<>(null, HttpStatus.OK, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        try {
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(new ResponseData<>(updatedUser, HttpStatus.OK, "User updated successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseData<>(null, HttpStatus.OK, "User not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<User>> deleteUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user != null) {
            userService.deleteUser(user);
            return ResponseEntity.ok(new ResponseData<>(user, HttpStatus.OK, "User deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseData<>(null, HttpStatus.OK, "User not found"));
    }

    // Endpoint to update nom and prenom
    @PutMapping("/{userId}/nom-prenom")
    public ResponseEntity<String> updateNomAndPrenom(
            @PathVariable("userId") Long userId,
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom
    ) {
        userService.updateNomAndPrenom(userId, nom, prenom);
        return ResponseEntity.ok("Nom and prenom updated successfully.");
    }

    // Endpoint to update password
    @PutMapping("/{userId}/password")
    public ResponseEntity<String> updatePassword(
            @PathVariable("userId") Long userId,
            @RequestParam("password") String password
    ) {

        userService.updatePassword(userId,passwordEncoder.encode( password));
        return ResponseEntity.ok("Password updated successfully.");
    }

    // Endpoint to update email
    @PutMapping("/{userId}/email")
    public ResponseEntity<String> updateEmail(
            @PathVariable("userId") Long userId,
            @RequestParam("email") String email
    ) {
        try {
            userService.updateEmail(userId, email);
            return ResponseEntity.ok("Email updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    // Endpoint to update telephone number
    @PutMapping("/{userId}/telephone")
    public ResponseEntity<String> updateTelephone(
            @PathVariable("userId") Long userId,
            @RequestParam("telephone") String telephone
    ) {
        userService.updateNumTel(userId, telephone);
        return ResponseEntity.ok("Telephone number updated successfully.");
    }

}
