package com.company.patien.dto.client;

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
public class PatientRequest {

    @NotEmpty
    @NotBlank(message = "First name field shod not be empty!")
    @Length(max = 120, message = "First name field shod have maximum of {max} characters!")
    private String firstName;

    @NotEmpty
    @NotBlank(message = "Last name field shod not be empty!")
    @Length(max = 120, message = "Last name field shod have maximum of {max} characters!")
    private String lastName;

    @NotEmpty
    @NotBlank(message = "Email field shod not be empty!")
    @Length(max = 120, message = "Email field shod have maximum of {max} characters!")
    private String email;

    @NotEmpty
    @NotBlank(message = "Phone number field shod not be empty!")
    @Length(max = 120, message = "Phone number field shod have maximum of {max} characters!")
    private String phoneNumber;

    @NotEmpty
    @NotBlank(message = "Address field shod not be empty!")
    @Length(max = 120, message = "Address field shod have maximum of {max} characters!")
    private String address;

}
