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
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.company.auth.mapper.RoleAdminMapper.mapToRoleAdminResponse;
import static com.company.auth.mapper.RoleAdminMapper.mapToRoleEntity;
import static com.company.auth.mapper.UserAdminMapper.mapToUserDetailsAdminResponse;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public Page<UserAdminResponse> findAllUsers(Pageable pageable) {
        log.info("Finding all users");
        return userRepository.
                findAll(pageable)
                .map(UserAdminMapper::mapToUserAdminResponse);
    }

    @Override
    public Page<UserAdminResponse> searchUsers(String search, Pageable pageable) {
        log.info("Searching users");
        return userRepository
                .searchByText(search, pageable)
                .map(UserAdminMapper::mapToUserAdminResponse);
    }

    @Override
    @Cacheable(value = "Users", key = "#result" ,unless ="#result == null ")
    public UserDetailsAdminResponse getUser(String username) {
        log.info("Getting user {}", username);
        UserEntity user = userRepository.getUserEntitiesByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Not find user by username: " + username + " !"
                        )
                );
        return mapToUserDetailsAdminResponse(user);
    }

    @Override
    @Transactional
    public void giveUserPermission(PermissionRequest permissionRequest) {
        log.info("Giving user permission {}", permissionRequest);
        UserEntity user = userRepository
                .getUserEntitiesByUsername(permissionRequest.getUsername())
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by username: " + permissionRequest.getUsername() + "!"
                        )
                );
        log.info("Got user permission {}", permissionRequest);
        RoleEntity role = roleRepository
                .findByNameIgnoreCase(permissionRequest.getName())
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Can not find role by name: " + permissionRequest.getName() + "!"
                        )
                );
        log.info("Got role {}", role);
        user.getRoles().add(role);
        log.info("Saving user permission {}", permissionRequest);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeUserPermission(PermissionRequest permissionRequest) {
        log.info("Removing user permission {}", permissionRequest);
        UserEntity user = userRepository
                .getUserEntitiesByUsername(permissionRequest.getUsername())
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by username: " + permissionRequest.getUsername() + "!"
                        )
                );
        log.info("Removing user permission {}", permissionRequest);
        RoleEntity role = roleRepository
                .findByNameIgnoreCase(permissionRequest.getName())
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Can not find role by name: " + permissionRequest.getName() + "!"
                        )
                );
        log.info("Removing role {}", role);
        user.getRoles().remove(role);
        log.info("Saving user permission {}", permissionRequest);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void clearUserPermissions(String username) {
        log.info("Clearing user permissions {}", username);
        UserEntity user = userRepository
                .getUserEntitiesByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by username: " + username + "!"
                        )
                );
        log.info("Clearing user permissions {}", username);
        user.getRoles().clear();
        log.info("Saving user permissions {}", username);
        userRepository.save(user);
    }

    @Override
    @Transactional
    @CacheEvict(value = "user_details", key = "#username", allEntries = true)
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
        log.info("Deleting user {}", username);
        userRepository.deleteByUsername(username);
    }

    @Override
    public Page<RoleAdminResponse> findAllRoles(Pageable pageable) {
        log.info("Finding all roles");
        return roleRepository
                .findAll(pageable)
                .map(RoleAdminMapper::mapToRoleAdminResponse);
    }

    @Override
    public Page<RoleAdminResponse> searchRoles(String searchText, Pageable pageable) {
        log.info("Searching roles");
        return roleRepository
                .searchByText(searchText, pageable)
                .map(RoleAdminMapper::mapToRoleAdminResponse);
    }

    @Override
    public RoleAdminResponse createRole(RoleAdminRequest roleAdminRequest) {
        log.info("Creating role {}", roleAdminRequest);
        RoleEntity roleEntity = mapToRoleEntity(roleAdminRequest);
        log.info("Saving role {}", roleEntity);
        roleRepository.save(roleEntity);
        return mapToRoleAdminResponse(roleEntity);
    }

    @Override
    @Transactional
    public RoleAdminResponse updateRole(RoleAdminRequest roleAdminRequest) {
        log.info("Updating role {}", roleAdminRequest);
        RoleEntity role = roleRepository
                .findByNameIgnoreCase(roleAdminRequest.getName())
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Can not find role by name: " + roleAdminRequest.getName() + "!"
                        )
                );
        if(!role.getVersion().equals(roleAdminRequest.getVersion())) {
            throw new RoleVersionNotValidException(
                    "Role version is not valid!"
            );
        }
        log.info("Updating role {}", role);
        role.setName(roleAdminRequest.getName());
        log.info("Saving role {}", role);
        role.setVersion(roleAdminRequest.getVersion());
        log.info("Updating role {}", role);
        roleRepository.save(role);
        return mapToRoleAdminResponse(role);
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
        log.info("Deleting role {}", name);
        roleRepository.deleteByNameIgnoreCase(name);
    }

}
