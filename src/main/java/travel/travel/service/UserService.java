package travel.travel.service;

import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    SimpleResponse saveUser(UserRequest userRequest);

    SimpleResponse createUser(UserRequest userRequest);

    List<UserResponse> findAllUsers();

    UserResponse findUserById(Long id);

    SimpleResponse updateUserById(Long id, UserRequest newUserRequest);

    SimpleResponse deleteUserById(Long id);

}
