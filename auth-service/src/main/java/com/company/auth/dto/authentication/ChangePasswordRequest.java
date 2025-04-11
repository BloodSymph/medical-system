package com.company.auth.dto.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {

    @NotEmpty
    @NotBlank(message = "Password field shod not be empty!")
    @Length(max = 120, message = "Password field shod have maximum of {max} characters!")
    private String oldPassword;

    @NotEmpty
    @NotBlank(message = "Password field shod not be empty!")
    @Length(max = 120, message = "Password field shod have maximum of {max} characters!")
    private String newPassword;

    @NotEmpty
    @NotBlank(message = "Password field shod not be empty!")
    @Length(max = 120, message = "Password field shod have maximum of {max} characters!")
    private String confirmPassword;

}
