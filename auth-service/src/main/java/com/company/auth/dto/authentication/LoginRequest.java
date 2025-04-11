package com.company.auth.dto.authentication;

import com.company.auth.util.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {

    @NotEmpty
    @NotBlank(message = "Username field shod not be empty!")
    @Length(max = 120, message = "Username field shod have maximum of {max} characters!")
    private String username;


    @NotEmpty
    @NotBlank(message = "Password field shod not be empty!")
    @Length(max = 120, message = "Password field shod have maximum of {max} characters!")
    @ValidPassword
    private String password;

}
