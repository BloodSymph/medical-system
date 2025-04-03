package com.company.auth.mapper;

import com.company.auth.dto.admin.role.RoleAdminResponse;
import com.company.auth.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleAdminMapper {

    public static RoleAdminResponse mapToRoleAdminResponse(RoleEntity roleEntity) {
        return RoleAdminResponse.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .created(roleEntity.getCreated())
                .updated(roleEntity.getUpdated())
                .version(roleEntity.getVersion())
                .build();
    }

    public static RoleEntity mapToRoleEntity(RoleAdminResponse roleAdminResponse) {
        return RoleEntity.builder()
                .name(roleAdminResponse.getName())
                .version(roleAdminResponse.getVersion())
                .build();
    }

}
