package travel.travel.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import travel.travel.config.jwt.JWTService;
import travel.travel.exception.UserAlreadyExistsException;
import travel.travel.model.dto.request.SignInRequest;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.AuthResponse;
import travel.travel.model.dto.response.JwtTokenResponse;
import travel.travel.model.dto.response.UserResponse;
import travel.travel.model.entity.User;
import travel.travel.model.enums.Role;
import travel.travel.model.mapper.UserMapper;
import travel.travel.repository.UserRepository;
import travel.travel.service.AuthService;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final UserMapper userMapper;

    public static final String MESSAGE_USER_EXISTS = "Пользователь с email %s уже существует.";
    public static final String MESSAGE_USER_REGISTERED = "Пользователь успешно зарегистрирован с email: %s.";
    public static final String MESSAGE_INVALID_CREDENTIALS = "Неверный email или пароль.";
    public static final String MESSAGE_SUCCESSFUL_SIGN_IN = "Успешный вход для пользователя: %s.";

    @Override
    public UserResponse signUp(UserRequest userRequest) {
        log.info("Регистрация пользователя с email: {}", userRequest.email());

        if (userRepository.existsByEmail(userRequest.email())) {
            throw new UserAlreadyExistsException(String.format(MESSAGE_USER_EXISTS, userRequest.email()));
        }

        String encodedPassword = passwordEncoder.encode(userRequest.password());

        User user = new User();
        user.setEmail(userRequest.email());
        user.setName(userRequest.name());
        user.setPhoneNumber(userRequest.phoneNumber());
        user.setPassword(encodedPassword);
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);

        log.info(String.format(MESSAGE_USER_REGISTERED, user.getEmail()));
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();

    }


    @Override
    public AuthResponse signIn(SignInRequest signInRequest) {
        log.info("Вход с email: {}", signInRequest.getUsername());

        User user = userRepository.findByEmail(signInRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(MESSAGE_INVALID_CREDENTIALS));

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            log.warn("Неверный пароль для пользователя: {}", signInRequest.getUsername());
            throw new BadCredentialsException(MESSAGE_INVALID_CREDENTIALS);
        }

        String token = jwtService.generateToken(user);

        JwtTokenResponse jwtTokenResponse = JwtTokenResponse.builder()
                .token(token)
                .issueAt(ZonedDateTime.now())
                .expiresAt(ZonedDateTime.now().plusDays(10))
                .build();

        log.info(String.format(MESSAGE_SUCCESSFUL_SIGN_IN, user.getEmail()));

        return AuthResponse.builder()
                .email(user.getEmail())
                .role(user.getRole().name())
                .token(jwtTokenResponse)
                .message("Успешный вход")
                .status(HttpStatus.OK)
                .build();
    }
}
