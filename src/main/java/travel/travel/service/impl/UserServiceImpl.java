package travel.travel.service.impl;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import travel.travel.exception.AlreadyExistException;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.UserResponse;
import travel.travel.model.entity.User;
import travel.travel.model.mapper.UserMapper;
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
    String MESSAGE_EMAIL_EXISTS = "User with email %s already exists.";
    String MESSAGE_USER_CREATED = "User created successfully with email: %s";

    @Override
    public SimpleResponse saveUser( @Valid  UserRequest userRequest) {
        log.info("Creating new user with email: {}", userRequest.email());
        User user = userMapper.toUser(userRequest);
        userRepository.save(user);
        return SimpleResponse.builder()
                .status(HttpStatus.CREATED)
                .message(String.format(MESSAGE_USER_CREATED, userRequest.email()))
                .timestamp(LocalDateTime.now())
                .build();
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
        return SimpleResponse.builder()
                .status(HttpStatus.CREATED)
                .message(String.format(MESSAGE_USER_CREATED, userRequest.email()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public List<UserResponse> findAllUsers() {
        return List.of();
    }

    @Override
    public UserResponse findUserById(Long id) {
        return null;
    }

    @Override
    public SimpleResponse updateUserById(Long id, UserRequest newUserRequest) {
        return null;
    }

    @Override
    public SimpleResponse deleteUserById(Long id) {
        return null;
    }
}

