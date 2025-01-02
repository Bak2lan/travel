package travel.travel.service;

import travel.travel.model.dto.request.SignInRequest;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.AuthResponse;
import travel.travel.model.dto.response.UserResponse;

public interface AuthService {
    UserResponse signUp(UserRequest userRequest);

    AuthResponse signIn(SignInRequest signInRequest);

}
