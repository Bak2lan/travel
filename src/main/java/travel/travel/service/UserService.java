package travel.travel.service;

import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import travel.travel.model.dto.SimpleResponse;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.UserResponse;
import travel.travel.model.dto.response.UserResponseForGetAll;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface UserService {
    UserResponse getById(Long id) throws ChangeSetPersister.NotFoundException;

    List<UserResponseForGetAll> getAllUsers(Pageable pageable);

    UserResponse updateUser(Long id, UserRequest userRequest) throws ChangeSetPersister.NotFoundException, AccessDeniedException, BadRequestException;

    SimpleResponse deleteUser(Long id) throws ChangeSetPersister.NotFoundException, AccessDeniedException;

    SimpleResponse saveUser(UserRequest userRequest) throws ChangeSetPersister.NotFoundException, AccessDeniedException;

}
