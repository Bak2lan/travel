package travel.travel.service.impl;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import travel.travel.config.jwt.JWTService;
import travel.travel.exception.AlreadyExistException;
import travel.travel.exception.BadRequestExeption;
import travel.travel.exception.NotFoundException;
import travel.travel.model.dto.request.SignInRequest;
import travel.travel.model.dto.request.SignUpRequest;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.SignUpAndInResponse;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.UserResponse;
import travel.travel.model.entity.Travel;
import travel.travel.model.entity.User;
import travel.travel.model.enums.Role;
import travel.travel.model.mapper.UserMapper;
import travel.travel.repository.TravelRepository;
import travel.travel.repository.UserRepository;
import travel.travel.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    public static final String MESSAGE_EMAIL_EXISTS = "User with email %s already exists.";
    public static final String MESSAGE_USER_CREATED = "User created successfully with email: %s";
    public static final String MESSAGE_USER_UPDATED = "User successfully updated with id: %d";
    public static final String MESSAGE_USER_NOT_FOUND = "User with id %d not found.";
    public static final String MESSAGE_USER_DELETED = "User successfully deleted with id: %d";
    private final TravelRepository travelRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Override
    public SignUpAndInResponse signUp(SignUpRequest signUpRequest) {
        if (signUpRequest.fullName().isBlank()) {
            log.warn("Attempting to sign up with empty full name");
            throw new BadRequestExeption("Full name can not be empty!");
        }
        if (userRepository.existsByEmail(signUpRequest.email())) {
            log.warn("Email {} is already exist", signUpRequest.email());
            throw new AlreadyExistException(String.format(MESSAGE_EMAIL_EXISTS, signUpRequest.email()));
        }
        Travel travel = travelRepository.findById(1L).orElseThrow(() -> {
            log.error("Travel with id {} is not found", 1);
            return new NotFoundException(String.format("Travel with id %s is not found", 1));
        });
        User user = new User();
        user.setEmail(signUpRequest.email());
        user.setName(signUpRequest.fullName());
        user.setTravel(travel);
        user.setRole(Role.ROLE_ADMIN);
        user.setPassword(passwordEncoder.encode(signUpRequest.password()));
        user.setPhoneNumber(signUpRequest.phoneNumber());
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return SignUpAndInResponse
                .builder()
                .email(user.getEmail())
                .role(user.getRole().name())
                .token(token)
                .build();
    }

    @Override
    public SignUpAndInResponse signIn(SignInRequest signInRequest) {

        if (!userRepository.existsByEmail(signInRequest.email())) {
            log.warn("Email {} is not found", signInRequest.email());
            throw new NotFoundException(String.format("Email %s is not found", signInRequest.email()));
        }
        User user = userRepository.findByEmail(signInRequest.email()).orElseThrow(() -> {
            log.error("Email {} is not found", signInRequest.email());
            return new NotFoundException(String.format("User with email %s is not found", signInRequest.email()));
        });
        if (!passwordEncoder.matches(signInRequest.password(), user.getPassword())) {
            log.warn("Password incorrect!");
            throw new BadRequestExeption("Password incorrect!");
        }
        String token = jwtService.generateToken(user);
        return SignUpAndInResponse
                .builder()
                .email(user.getEmail())
                .role(user.getRole().name())
                .token(token)
                .build();
    }

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
}