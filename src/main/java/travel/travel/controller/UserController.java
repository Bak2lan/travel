package travel.travel.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.service.UserService;


@RestController
@RequestMapping("/api/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<SimpleResponse> saveUser(@Valid @RequestBody UserRequest userRequest) {
        SimpleResponse response = userService.saveUser(userRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<SimpleResponse> createUser( @Valid @RequestBody UserRequest userRequest) {
        SimpleResponse response = userService.createUser(userRequest);
        return ResponseEntity.status(response.getStatus()).body(response);

    }
}
