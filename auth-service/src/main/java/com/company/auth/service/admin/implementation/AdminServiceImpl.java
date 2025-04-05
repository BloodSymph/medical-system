package com.company.auth.service.admin.implementation;

import com.company.auth.dto.admin.role.PermissionRequest;
import com.company.auth.dto.admin.role.RoleAdminRequest;
import com.company.auth.dto.admin.role.RoleAdminResponse;
import com.company.auth.dto.admin.user.UserAdminResponse;
import com.company.auth.dto.admin.user.UserDetailsAdminResponse;
import com.company.auth.entity.RoleEntity;
import com.company.auth.entity.UserEntity;
import com.company.auth.exception.exceptions.role.RoleNotFoundException;
import com.company.auth.exception.exceptions.role.RoleVersionNotValidException;
import com.company.auth.exception.exceptions.user.UserNotFoundException;
import com.company.auth.exception.exceptions.user.UserVersionNotValidException;
import com.company.auth.mapper.RoleAdminMapper;
import com.company.auth.mapper.UserAdminMapper;
import com.company.auth.repository.RoleRepository;
import com.company.auth.repository.UserRepository;
import com.company.auth.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.auth.mapper.RoleAdminMapper.mapToRoleAdminResponse;
import static com.company.auth.mapper.RoleAdminMapper.mapToRoleEntity;
import static com.company.auth.mapper.UserAdminMapper.mapToUserDetailsAdminResponse;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public Page<UserAdminResponse> findAllUsers(Pageable pageable) {
        return userRepository.
                findAll(pageable)
                .map(UserAdminMapper::mapToUserAdminResponse);
    }

    @Override
    public Page<UserAdminResponse> searchUsers(String search, Pageable pageable) {
        return userRepository
                .searchByText(search, pageable)
                .map(UserAdminMapper::mapToUserAdminResponse);
    }

    @Override
    public UserAdminResponse getUser(String username) {
        UserEntity user = userRepository.getUserEntitiesByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by username: " + username + "!"
                        )
                );
        return mapToUserDetailsAdminResponse(user);
    }

    @Override
    @Transactional
    public void giveUserPermission(PermissionRequest permissionRequest) {
        UserEntity user = userRepository
                .getUserEntitiesByUsername(permissionRequest.getUsername())
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by username: " + permissionRequest.getUsername() + "!"
                        )
                );
        RoleEntity role = roleRepository
                .findByNameIgnoreCase(permissionRequest.getName())
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Can not find role by name: " + permissionRequest.getName() + "!"
                        )
                );
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeUserPermission(PermissionRequest permissionRequest) {
        UserEntity user = userRepository
                .getUserEntitiesByUsername(permissionRequest.getUsername())
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by username: " + permissionRequest.getUsername() + "!"
                        )
                );
        RoleEntity role = roleRepository
                .findByNameIgnoreCase(permissionRequest.getName())
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Can not find role by name: " + permissionRequest.getName() + "!"
                        )
                );
        user.getRoles().remove(role);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void clearUserPermissions(String username) {
        UserEntity user = userRepository
                .getUserEntitiesByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by username: " + username + "!"
                        )
                );
        user.getRoles().clear();
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserByUsername(String username, Long version) {
        if(!userRepository.existsByUsernameIgnoreCase(username)) {
            throw new UserNotFoundException(
                    "Can not find user by username: " + username + "!"
            );
        }
        if (!userRepository.existsByVersion(version)) {
            throw new UserVersionNotValidException(
                    "User version is not valid!"
            );
        }
        userRepository.deleteByUsername(username);
    }

    @Override
    public Page<RoleAdminResponse> findAllRoles(Pageable pageable) {
        return roleRepository
                .findAll(pageable)
                .map(RoleAdminMapper::mapToRoleAdminResponse);
    }

    @Override
    public Page<RoleAdminResponse> searchRoles(String searchText, Pageable pageable) {
        return roleRepository
                .searchByText(searchText, pageable)
                .map(RoleAdminMapper::mapToRoleAdminResponse);
    }

    @Override
    public RoleAdminResponse createRole(RoleAdminRequest roleAdminRequest) {
        RoleEntity roleEntity = mapToRoleEntity(roleAdminRequest);
        roleRepository.save(roleEntity);
        return mapToRoleAdminResponse(roleEntity);
    }

    @Override
    @Transactional
    public RoleAdminResponse updateRole(RoleAdminRequest roleAdminRequest) {
        return null;
    }

    @Override
    @Transactional
    public void deleteRoleByName(String name, Long version) {
        if(!roleRepository.existsByNameIgnoreCase(name)) {
            throw new RoleNotFoundException(
                    "Can not find role by name: " + name + "!"
            );
        }
        if (!roleRepository.existsByVersion(version)){
            throw new RoleVersionNotValidException(
                    "Role version is not valid!"
            );
        }
        roleRepository.deleteByNameIgnoreCase(name);
    }

}
