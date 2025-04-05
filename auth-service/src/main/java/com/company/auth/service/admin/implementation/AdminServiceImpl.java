package com.company.auth.service.admin.implementation;

import com.company.auth.dto.admin.role.PermissionRequest;
import com.company.auth.dto.admin.role.RoleAdminResponse;
import com.company.auth.dto.admin.user.UserAdminResponse;
import com.company.auth.dto.admin.user.UserDetailsAdminResponse;
import com.company.auth.repository.RoleRepository;
import com.company.auth.repository.UserRepository;
import com.company.auth.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public Page<UserAdminResponse> findAllUsers(Pageable pageable) {
        return null;
    }

    @Override
    public Page<UserAdminResponse> searchUsers(String search, Pageable pageable) {
        return null;
    }

    @Override
    public UserAdminResponse getUser(String username) {
        return null;
    }

    @Override
    public UserDetailsAdminResponse giveUserPermission(PermissionRequest permissionRequest) {
        return null;
    }

    @Override
    public void removeUserPermission(PermissionRequest permissionRequest) {

    }

    @Override
    public void clearUserPermissions(String username) {

    }

    @Override
    public void deleteUserByUsername(String username, Long version) {

    }

    @Override
    public Page<RoleAdminResponse> findAllRoles(Pageable pageable) {
        return null;
    }

    @Override
    public Page<RoleAdminResponse> searchRoles(String search, Pageable pageable) {
        return null;
    }

    @Override
    public RoleAdminResponse getRole(Long roleId) {
        return null;
    }

    @Override
    public RoleAdminResponse createRole(RoleAdminResponse roleAdminResponse) {
        return null;
    }

    @Override
    public RoleAdminResponse updateRole(RoleAdminResponse roleAdminResponse) {
        return null;
    }

    @Override
    public void deleteRoleByName(String name, Long version) {

    }

}
