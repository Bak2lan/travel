package travel.travel.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import travel.travel.exception.NotFoundException;
import travel.travel.model.dto.SimpleResponse;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.UserResponse;
import travel.travel.model.dto.response.UserResponseForGetAll;
import travel.travel.model.entity.Travel;
import travel.travel.model.entity.User;
import travel.travel.model.enums.Role;
import travel.travel.repository.TravelRepository;
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
    private final TravelRepository travelRepository;
    @Override
    public UserResponse getById(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
            return UserResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .password(user.getPassword())
                    .name(user.getName())
                    .role(user.getRole())
                    .build();

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", e);
        }
    }
    @Override
    public List<UserResponseForGetAll> getAllUsers(Pageable pageable) {
        List<UserResponseForGetAll> filteredUsers = new ArrayList<>();

        try {
            List<UserResponseForGetAll> users = userRepository.getAllUsers(pageable);
            if (users == null || users.isEmpty()) {
                throw new IllegalArgumentException("User list cannot be null or empty");
            }

            for (UserResponseForGetAll user : users) {
                if (user == null) {
                    throw new IllegalArgumentException("User cannot be null");
                }

                if (user.getRole() == null) {
                    throw new IllegalArgumentException("User role cannot be null");
                }


                if (Role.ROLE_ADMIN.name().equals(user.getRole().name())) {
                    filteredUsers.add(user);
                }
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);

        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected null pointer error", e);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", e);
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
            userToUpdate.setName(userRequest.getName());
        }

        if (userRequest.getEmail() != null) {
            userToUpdate.setEmail(userRequest.getEmail());
        }

        if (userRequest.getPassword() != null) {
            userToUpdate.setPassword(userRequest.getPassword());
        }
        if (Role.ROLE_ADMIN.name().equals(currentUser.getRole().name())) {
            userRepository.save(userToUpdate);
        }

        return UserResponse.builder()
                .id(userToUpdate.getId())
                .email(userToUpdate.getEmail())
                .phoneNumber(userToUpdate.getPhoneNumber())
                .name(userToUpdate.getName())
                .build();
    }

    @Override
    public SimpleResponse deleteUser(Long id) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        if (Role.ROLE_ADMIN.name().equals(userToUpdate.getRole().name())) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.FORBIDDEN)
                    .message("Очурууга мүмкүн эмес: администратордун ролу бар колдонуучуну өчүрүүгө тыюу салынат")
                    .build();
        }

        userRepository.delete(userToUpdate);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Колдонуучу ийгиликтүү өчүрүлдү")
                .build();
    }

    @Override
    public SimpleResponse saveUser(UserRequest userRequest) throws ChangeSetPersister.NotFoundException, AccessDeniedException {
        if (userRequest == null) {
            throw new IllegalArgumentException("userRequest must not be null");
        }
        Travel travel = travelRepository.findById(1L)
                .orElseThrow(() -> new NotFoundException("Travel not found"));
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());
        user.setRole(Role.ROLE_USER);
        user.setTravel(travel);
        userRepository.save(user);
        return new SimpleResponse(HttpStatus.OK, "Колдонуучу ийгиликтүү өчүрүлдү");
    }

    }

