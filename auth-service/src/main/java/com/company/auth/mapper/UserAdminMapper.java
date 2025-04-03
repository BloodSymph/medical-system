package com.company.auth.mapper;

import com.company.auth.dto.admin.user.UserAdminResponse;
import com.company.auth.dto.admin.user.UserDetailsAdminResponse;
import com.company.auth.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserAdminMapper {

    public static UserAdminResponse mapToUserAdminResponse(UserEntity userEntity) {
        return UserAdminResponse.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .created(userEntity.getCreated())
                .updated(userEntity.getUpdated())
                .version(userEntity.getVersion())
                .build();
    }

    public static UserDetailsAdminResponse mapToUserDetailsAdminResponse(UserEntity userEntity) {
        return UserDetailsAdminResponse.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .roles(
                        userEntity.getRoles()
                                .stream()
                                .map(
                                        RoleAdminMapper::mapToRoleAdminResponse
                                ).collect(
                                        Collectors.toList()
                                )
                )
                .created(userEntity.getCreated())
                .updated(userEntity.getUpdated())
                .version(userEntity.getVersion())
                .build();
    }

}
