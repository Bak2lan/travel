package travel.travel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.travel.model.dto.request.SignInRequest;
import travel.travel.model.dto.request.SignUpRequest;
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
    public UserResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
       return userService.signUp(signUpRequest);
    }

    @Operation(summary = "User login")
    @PostMapping("/sign-in")
    public AuthResponse signIn(@Valid @RequestBody SignInRequest signInRequest) {
        return userService.signIn(signInRequest);
    }
}