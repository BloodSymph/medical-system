package com.company.auth.controller;

import com.company.auth.dto.admin.user.UserAdminResponse;
import com.company.auth.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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



}
