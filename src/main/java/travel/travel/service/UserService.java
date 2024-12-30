package travel.travel.service;


import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.UserResponse;

public interface UserService {

    SimpleResponse createUser(UserRequest userRequest);

    UserResponse getUserById(Long id);

    SimpleResponse updateUserById(Long id, UserRequest userRequest);

    SimpleResponse deleteUserById(Long id);


}
