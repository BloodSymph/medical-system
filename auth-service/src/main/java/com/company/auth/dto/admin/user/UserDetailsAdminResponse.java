package com.company.auth.dto.admin.user;

import com.company.auth.dto.admin.role.RoleAdminResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDetailsAdminResponse extends UserAdminResponse {

    private List<RoleAdminResponse> roles;

}
