package ena.api.zitona.controllers;

import ena.api.zitona.entitys.User;
import ena.api.zitona.response.ResponseData;
import ena.api.zitona.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseData<>(null, HttpStatus.NOT_FOUND, "User not found"));
    }

    @PostMapping
    public ResponseEntity<ResponseData<User>> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseData<>(savedUser, HttpStatus.CREATED, "User created successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData<>(null, HttpStatus.NOT_FOUND, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        try {
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(new ResponseData<>(updatedUser, HttpStatus.OK, "User updated successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData<>(null, HttpStatus.NOT_FOUND, "User not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<User>> deleteUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user != null) {
            userService.deleteUser(user);
            return ResponseEntity.ok(new ResponseData<>(user, HttpStatus.OK, "User deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData<>(null, HttpStatus.NOT_FOUND, "User not found"));
    }

}
