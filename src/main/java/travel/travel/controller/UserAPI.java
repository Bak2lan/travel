package travel.travel.controller;

import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import travel.travel.exception.NotFoundException;
import travel.travel.model.dto.SimpleResponse;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.UserResponse;
import travel.travel.model.dto.response.UserResponseForGetAll;
import travel.travel.service.UserService;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
@Tag(name = "User Api", description = "User Api")
public class UserAPI {
    private final UserService userService;

    @PutMapping("/{id}/update")
    @Operation(summary = "Update users", description = "Update users by id from database")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) throws ChangeSetPersister.NotFoundException, BadRequestException, AccessDeniedException, AccessDeniedException, BadRequestException {
        return userService.updateUser(id, userRequest);
    }

    @GetMapping("/allUsers")
    @Operation(summary = "Get all users", description = "Get all users from database")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<UserResponseForGetAll> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get user by id ", description = "User profile for administrator")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public UserResponse getById(@PathVariable Long id) throws NotFoundException, ChangeSetPersister.NotFoundException {
        return userService.getById(id);
    }
    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Delete users", description = "Delete users by id from database")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse deleteUser(@PathVariable Long id) throws NotFoundException, AccessDeniedException, ChangeSetPersister.NotFoundException {
        return userService.deleteUser(id);
    }
    @PostMapping("/save")
    @Operation(summary = "Save mentor", description = "Save new mentor by administrator")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse save(@RequestBody UserRequest request) throws NotFoundException, AccessDeniedException, ChangeSetPersister.NotFoundException {
        return userService.saveUser(request);

    }
}