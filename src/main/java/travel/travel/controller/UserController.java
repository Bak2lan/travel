package travel.travel.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.service.UserService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/users")
public class UserController {
    UserService userService;

    @PostMapping("/create")
   public ResponseEntity<SimpleResponse> createUser( @Valid @RequestBody UserRequest userRequest) {
        SimpleResponse response = userService.createUser(userRequest);
        return ResponseEntity.status(response.getStatus()).body(response);

    }

}
