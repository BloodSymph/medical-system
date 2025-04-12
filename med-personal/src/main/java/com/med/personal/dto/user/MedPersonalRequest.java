package com.med.personal.dto.user;

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
public class MedPersonalRequest {

    @NotEmpty
    @NotBlank(message = "First name field shod not be empty!")
    @Length(max = 120, message = "First name field shod have maximum of {max} characters!")
    private String firstName;

    @NotEmpty
    @NotBlank(message = "Last name field shod not be empty!")
    @Length(max = 120, message = "Last name field shod have maximum of {max} characters!")
    private String lastName;

    @NotEmpty
    @NotBlank(message = "Phone number field shod not be empty!")
    @Length(max = 120, message = "Phone number field shod have maximum of {max} characters!")
    private String phoneNumber;

    @NotEmpty
    @NotBlank(message = "Email field shod not be empty!")
    @Length(max = 120, message = "Email field shod have maximum of {max} characters!")
    private String email;

    @NotEmpty
    @NotBlank(message = "Address field shod not be empty!")
    @Length(max = 120, message = "Address field shod have maximum of {max} characters!")
    private String address;

    @NotEmpty
    @NotBlank(message = "Specialty field shod not be empty!")
    @Length(max = 120, message = "Specialty field shod have maximum of {max} characters!")
    private String specialty;

    @NotNull(message = "Specialty code field shod not contains null value!")
    private Long specialtyCode;

    @NotNull(message = "Version field shod not contains null value!")
    private Long version;


}
