package travel.travel.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.UserResponse;
import travel.travel.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Validated
public class UserController {
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<SimpleResponse> saveUser(@Valid @RequestBody UserRequest userRequest) {
        SimpleResponse response = userService.saveUser(userRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<SimpleResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        SimpleResponse response = userService.createUser(userRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> allUsers() {
        List<UserResponse> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/by_id/{id}")
    public ResponseEntity<UserResponse> userById(@PathVariable Long id) {
        UserResponse userResponse = userService.findUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/updated/{id}")
    public ResponseEntity<SimpleResponse> updateUserById(@PathVariable Long id, @RequestBody UserRequest newUserRequest) {
        SimpleResponse response = userService.updateUserById(id, newUserRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SimpleResponse> deleteById(@PathVariable Long id) {
        SimpleResponse response = userService.deleteUserById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}