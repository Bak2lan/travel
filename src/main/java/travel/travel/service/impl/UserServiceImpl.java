package travel.travel.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import travel.travel.exception.NotFoundException;
import travel.travel.model.dto.SimpleResponse;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.UserResponse;
import travel.travel.model.entity.User;
import travel.travel.model.enums.Role;
import travel.travel.repository.UserRepository;
import travel.travel.service.UserService;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserResponse getById(Long id) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .name(user.getName())
                .build();
    }

    @Override
    public List<UserResponse> getAllUsers(Pageable pageable) {
        List<UserResponse> filteredUsers = new ArrayList<>();

        try {
            List<UserResponse> users = userRepository.getAllUsers(pageable);

            if (users == null) {
                throw new IllegalArgumentException("User list cannot be null");
            }

            for (UserResponse user : users) {
                if (user == null) {
                    throw new IllegalArgumentException("User cannot be null");
                }
                if (user.getRole() == null) {
                    throw new IllegalArgumentException("User role cannot be null");
                }

                if (Role.ROLE_USER.name().equals(user.getRole().name())) {
                    filteredUsers.add(user);
                }
            }
        } catch (NullPointerException e) {
            // NullPointerException: жакынча кандайдыр бир объект null болуп калышы мүмкүн
            System.err.println("Null pointer exception: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // IllegalArgumentException: аргументтер туура эмес болушу мүмкүн
            System.err.println("Illegal argument: " + e.getMessage());
        } catch (Exception e) {
            // Башка күтүлбөгөн каталар
            System.err.println("Unexpected error: " + e.getMessage());
        }

        return filteredUsers;
    }
    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) throws ChangeSetPersister.NotFoundException, AccessDeniedException, BadRequestException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();


        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));


        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));


        if (!currentUser.getId().equals(userToUpdate.getId())) {
            throw new AccessDeniedException("Сиз бул колдонуучунун маалыматтарын өзгөртүүгө уруксатыңыз жок");
        }



        if (userRequest.getPhoneNumber() != null) {
            userToUpdate.setPhoneNumber(userRequest.getPhoneNumber());
        }

        if (userRequest.getName() != null) {
            userToUpdate.setName(userRequest.getName());  // phoneNumber эмес, аты өзгөртүлүшү керек
        }

        if (userRequest.getEmail() != null) {
            userToUpdate.setEmail(userRequest.getEmail());  // email жаңыртуу
        }

        if (userRequest.getPassword() != null) {
            userToUpdate.setPassword(userRequest.getPassword());  // паролду жаңыртуу
        }


        userRepository.save(userToUpdate);


        return UserResponse.builder()
                .id(userToUpdate.getId())
                .email(userToUpdate.getEmail())
                .phoneNumber(userToUpdate.getPhoneNumber())
                .name(userToUpdate.getName())  // аты кошулду
                .build();
    }

    @Override
    public SimpleResponse deleteUser(Long id) throws ChangeSetPersister.NotFoundException, AccessDeniedException {
        return null;
    }
}



