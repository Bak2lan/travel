package travel.travel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.travel.model.dto.request.SignInRequest;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.AuthResponse;
import travel.travel.model.dto.response.UserResponse;
import travel.travel.service.UserService;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for authentication and user management")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    @Operation(summary = "User register")
    public ResponseEntity<UserResponse> signUp(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.signUp(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @Operation(summary = "User login")
    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        AuthResponse authResponse = userService.signIn(signInRequest);
        return ResponseEntity.ok().body(authResponse);
    }
}
