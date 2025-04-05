package com.company.auth.service.admin;

import com.company.auth.dto.admin.role.PermissionRequest;
import com.company.auth.dto.admin.role.RoleAdminResponse;
import com.company.auth.dto.admin.user.UserAdminResponse;
import com.company.auth.dto.admin.user.UserDetailsAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    Page<UserAdminResponse> findAllUsers(Pageable pageable);

    Page<UserAdminResponse> searchUsers(String search, Pageable pageable);

    UserAdminResponse getUser(String username);

    UserDetailsAdminResponse giveUserPermission(PermissionRequest permissionRequest);

    void removeUserPermission(PermissionRequest permissionRequest);

    void clearUserPermissions(String username);

    void deleteUserByUsername(String username, Long version);

    Page<RoleAdminResponse> findAllRoles(Pageable pageable);

    Page<RoleAdminResponse> searchRoles(String search, Pageable pageable);

    RoleAdminResponse getRole(Long roleId);

    RoleAdminResponse createRole(RoleAdminResponse roleAdminResponse);

    RoleAdminResponse updateRole(RoleAdminResponse roleAdminResponse);

    void deleteRoleByName(String name, Long version);

}
