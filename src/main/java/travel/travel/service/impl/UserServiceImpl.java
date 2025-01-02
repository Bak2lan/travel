package travel.travel.service.impl;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import travel.travel.exception.AlreadyExistException;
import travel.travel.exception.NotFoundException;
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
    public static final String MESSAGE_EMAIL_EXISTS = "User with email %s already exists.";
    public static final String MESSAGE_USER_CREATED = "User created successfully with email: %s";
    public static final String MESSAGE_USER_UPDATED = "User successfully updated with id: %d";
    public static final String MESSAGE_USER_NOT_FOUND = "User with id %d not found.";
    public static final String MESSAGE_USER_DELETED = "User successfully deleted with id: %d";

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