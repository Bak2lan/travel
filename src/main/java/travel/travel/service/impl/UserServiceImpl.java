package travel.travel.service.impl;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.UserResponse;
import travel.travel.model.entity.User;
import travel.travel.model.mapper.UserMapper;
import travel.travel.repository.UserRepository;
import travel.travel.service.UserService;

import java.time.LocalDateTime;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public SimpleResponse createUser(UserRequest userRequest) {
        log.info("creating new user with email: {}",userRequest.email());
        User user = userMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        log.info("User successfully created with id: {}", savedUser.getId());
        return SimpleResponse.builder()
                .status(HttpStatus.CREATED)
                .message("success")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public UserResponse getUserById(Long id) {
        return null;
    }

    @Override
    public SimpleResponse updateUserById(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public SimpleResponse deleteUserById(Long id) {
        return null;
    }
}
