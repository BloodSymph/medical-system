package com.company.auth.dto.admin.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleAdminRequest {

    @NotEmpty
    @NotBlank(message = "Name field shod not be empty!")
    @Length(max = 120, message = "Name field shod have maximum of {max} characters!")
    private String name;

    @NotNull(message = "Version field shod not contains null value!")
    private Long version;

}
