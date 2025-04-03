package com.company.auth.dto.admin.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequest {

    @NotEmpty
    @NotBlank(message = "Username field shod not be empty!")
    @Length(max = 120, message = "Username field shod have maximum of {max} characters!")
    private String username;

    @NotEmpty
    @NotBlank(message = "Role name field shod not be empty!")
    @Length(max = 120, message = "Role name field shod have maximum of {max} characters!")
    private String name;

}
