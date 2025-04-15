package com.company.auth.service.admin;

import com.company.auth.dto.admin.role.PermissionRequest;
import com.company.auth.dto.admin.role.RoleAdminRequest;
import com.company.auth.dto.admin.role.RoleAdminResponse;
import com.company.auth.dto.admin.user.UserAdminResponse;
import com.company.auth.dto.admin.user.UserDetailsAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    Page<UserAdminResponse> findAllUsers(Pageable pageable);

    Page<UserAdminResponse> searchUsers(String search, Pageable pageable);

    UserDetailsAdminResponse getUser(String username);

    void giveUserPermission(PermissionRequest permissionRequest);

    void removeUserPermission(PermissionRequest permissionRequest);

    void clearUserPermissions(String username);

    void deleteUserByUsername(String username, Long version);

    Page<RoleAdminResponse> findAllRoles(Pageable pageable);

    Page<RoleAdminResponse> searchRoles(String searchText, Pageable pageable);

    RoleAdminResponse createRole(RoleAdminRequest roleAdminRequest);

    RoleAdminResponse updateRole(RoleAdminRequest roleAdminRequest);

    void deleteRoleByName(String name, Long version);

    @Scheduled(fixedDelay = 5000)
    void  cacheEvict();


}
