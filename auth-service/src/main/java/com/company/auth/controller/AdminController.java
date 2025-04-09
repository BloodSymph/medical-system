package com.company.auth.controller;

import com.company.auth.dto.admin.role.PermissionRequest;
import com.company.auth.dto.admin.role.RoleAdminRequest;
import com.company.auth.dto.admin.role.RoleAdminResponse;
import com.company.auth.dto.admin.user.UserAdminResponse;
import com.company.auth.service.admin.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/auth-service/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserAdminResponse> getUsers(@PageableDefault(
            page = 0,
            size = 25,
            sort = "username",
            direction = Sort.Direction.ASC) Pageable pageable){
        return adminService.findAllUsers(pageable);
    }

    @GetMapping("/users/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserAdminResponse> searchUsers(
            @RequestParam(value = "search", defaultValue = "", required = false) String search,
            @PageableDefault(
                page = 0,
                size = 25,
                sort = "username",
                direction = Sort.Direction.ASC) Pageable pageable){
        return adminService.searchUsers(search, pageable);
    }

    @GetMapping("/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserAdminResponse getUser(
            @PathVariable(value = "username") String username){
        return adminService.getUser(username);
    }

    @PostMapping("/users/give-permission")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> giveUserPermission(
            @Valid @RequestBody PermissionRequest permissionRequest){
        adminService.giveUserPermission(permissionRequest);
        return new ResponseEntity<>(
                "Permission is successful given!",
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/users/remove-permission")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeUserPermission(
            @Valid @RequestBody PermissionRequest permissionRequest) {
        adminService.removeUserPermission(permissionRequest);
        return new ResponseEntity<>(
                "Permission is successful removed!",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/users/{username}/clear-permissions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> clearUserPermission(
            @PathVariable(value = "username") String username){
        adminService.clearUserPermissions(username);
        return new ResponseEntity<>(
                "Clear all permissions successful!",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/users/{username}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteUser(
            @PathVariable(value = "username") String username,
            @RequestParam(value = "version") Long version ) {
        adminService.deleteUserByUsername(username, version);
        return new ResponseEntity<>(
                "User successfully deleted!",
                HttpStatus.OK
        );
    }

    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    public Page<RoleAdminResponse> getAllRoles(
            @PageableDefault(
                    page = 0,
                    size = 25,
                    sort = "name",
                    direction = Sort.Direction.ASC) Pageable pageable){
        return adminService.findAllRoles(pageable);
    }

    @GetMapping("/roles/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<RoleAdminResponse> searchRoles(
            @RequestParam(value = "search", defaultValue = "", required = false) String search,
            @PageableDefault(
                    page = 0,
                    size = 25,
                    sort = "name",
                    direction = Sort.Direction.ASC) Pageable pageable) {
        return adminService.searchRoles(search, pageable);
    }

    @PostMapping("/roles/create")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleAdminResponse createRole(
            @Valid @RequestBody RoleAdminRequest roleAdminRequest) {
        return adminService.createRole(roleAdminRequest);
    }

    @PutMapping("/roles/update")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleAdminResponse updateRole(
            @Valid @RequestBody RoleAdminRequest roleAdminRequest) {
        return adminService.updateRole(roleAdminRequest);
    }

    @DeleteMapping("/roles/{name}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteRole(
            @PathVariable(value = "name") String name,
            @RequestParam(value = "version") Long version){
        adminService.deleteRoleByName(name, version);
        return new ResponseEntity<>(
                "Role successfully deleted!",
                HttpStatus.OK
        );
    }

}
