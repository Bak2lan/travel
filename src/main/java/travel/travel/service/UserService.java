package travel.travel.service;

import travel.travel.model.dto.request.SignInRequest;
import travel.travel.model.dto.request.SignUpRequest;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.SignUpAndInResponse;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    SignUpAndInResponse signUp(SignUpRequest signUpRequest);

    SignUpAndInResponse signIn(SignInRequest signInRequest);

    SimpleResponse saveUser(UserRequest userRequest);

    SimpleResponse createUser(UserRequest userRequest);

    List<UserResponse> findAllUsers();

    UserResponse findUserById(Long id);

    SimpleResponse updateUserById(Long id, UserRequest newUserRequest);

    SimpleResponse deleteUserById(Long id);

}
