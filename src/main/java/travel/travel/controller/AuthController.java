package travel.travel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import travel.travel.model.dto.request.SignInRequest;
import travel.travel.model.dto.request.SignUpRequest;
import travel.travel.model.dto.response.SignUpAndInResponse;
import travel.travel.service.UserService;
@Tag(name = "REST APIs for Authentication",
description = "REST APIs to sign up and sign in")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
@Validated
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @Operation(summary = "Sign Up",
    description = "REST API to sign up")
    @PostMapping("/signUp")
    public ResponseEntity<SignUpAndInResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        SignUpAndInResponse signUpAndInResponse = userService.signUp(signUpRequest);
        return ResponseEntity.ok(signUpAndInResponse);
    }
    @Operation(summary = "Sign In",
    description = "REST API to sign in")
    @PostMapping("/signIn")
    public ResponseEntity<SignUpAndInResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        SignUpAndInResponse signUpAndInResponse = userService.signIn(signInRequest);
        return ResponseEntity.ok(signUpAndInResponse);
    }

}
