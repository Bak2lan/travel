package travel.travel.service.impl;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import travel.travel.config.jwt.JWTService;
import travel.travel.exception.AlreadyExistException;
import travel.travel.exception.NotFoundException;
import travel.travel.exception.UserAlreadyExistsException;
import travel.travel.model.dto.request.SignInRequest;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.AuthResponse;
import travel.travel.model.dto.response.JwtTokenResponse;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.UserResponse;
import travel.travel.model.entity.User;
import travel.travel.model.enums.Role;
import travel.travel.model.mapper.UserMapper;
import travel.travel.repository.UserRepository;
import travel.travel.service.UserService;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    UserRepository userRepository;
    UserMapper userMapper;
    public static final String MESSAGE_EMAIL_EXISTS = "User with email %s already exists.";
    public static final String MESSAGE_USER_CREATED = "User created successfully with email: %s";
    public static final String MESSAGE_USER_UPDATED = "User successfully updated with id: %d";
    public static final String MESSAGE_USER_NOT_FOUND = "User with id %d not found.";
    public static final String MESSAGE_USER_DELETED = "User successfully deleted with id: %d";

    public static final String MESSAGE_USER_EXISTS = "User with email %s already exists.";
    public static final String MESSAGE_USER_REGISTERED = "User successfully registered with email: %s.";
    public static final String MESSAGE_INVALID_CREDENTIALS = "Invalid email or password.";
    public static final String MESSAGE_SUCCESSFUL_SIGN_IN = "Successful sign-in for user: %s.";
    //TODO password encoder
    @Override
    public SimpleResponse saveUser(@Valid UserRequest userRequest) {
        log.info("Creating new user with email: {}", userRequest.email());

        if (userRepository.existsByEmail(userRequest.email())) {
            throw new AlreadyExistException(String.format(MESSAGE_EMAIL_EXISTS, userRequest.email()));
        }
        User user = userMapper.toUser(userRequest);
        userRepository.save(user);
        return buildResponse(HttpStatus.CREATED, String.format(MESSAGE_USER_CREATED, userRequest.email()));
    }

    @Override
    public SimpleResponse createUser(UserRequest userRequest) {
        log.info("Attempting to create a new user with email: {}", userRequest.email());

        userRepository.findByEmail(userRequest.email())
                .ifPresent(existingUser -> {
                    throw new AlreadyExistException(String.format(MESSAGE_EMAIL_EXISTS, userRequest.email()));
                });

        User user = userMapper.toUser(userRequest);
        User savedUser = userRepository.save(user);

        log.info("User successfully created with id: {}", savedUser.getId());
        return buildResponse(HttpStatus.CREATED, String.format(MESSAGE_USER_CREATED, userRequest.email()));
    }

    @Override
    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserResponse(users);
    }

    @Override
    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(MESSAGE_USER_NOT_FOUND, id)));
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional
    public SimpleResponse updateUserById(Long id, UserRequest newUserRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(MESSAGE_USER_NOT_FOUND, id)));
        userMapper.updateEntityFromRequest(newUserRequest, user);
        userRepository.save(user);
        log.info("User with id {} successfully updated", user.getId());
        return buildResponse(HttpStatus.OK, String.format(MESSAGE_USER_UPDATED, user.getId()));
    }

    @Override
    @Transactional
    public SimpleResponse deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(MESSAGE_USER_NOT_FOUND, id)));
        userRepository.delete(user);
        log.info("User with id {} successfully deleted", id);
        return buildResponse(HttpStatus.OK, String.format(MESSAGE_USER_DELETED, id));
    }

    private SimpleResponse buildResponse(HttpStatus status, String message) {
        return SimpleResponse.builder()
                .status(status)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @Override
    public UserResponse signUp(UserRequest userRequest) {
        log.info("Registering user with email: {}", userRequest.email());

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
        log.info("Signing in with email: {}", signInRequest.getUsername());

        User user = userRepository.findByEmail(signInRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(MESSAGE_INVALID_CREDENTIALS));

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            log.warn("Invalid password for user: {}", signInRequest.getUsername());
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
                .message("Successful sign-in")
                .status(HttpStatus.OK)
                .build();
    }
}