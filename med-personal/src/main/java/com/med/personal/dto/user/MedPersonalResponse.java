package com.med.personal.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedPersonalResponse {

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    private String username;

    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    private String email;

    private String address;

    private String specialty;

    @JsonProperty(value = "specialty_code")
    private Long specialtyCode;

}
