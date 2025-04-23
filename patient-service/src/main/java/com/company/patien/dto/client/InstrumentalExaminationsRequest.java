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
public class InstrumentalExaminationsRequest {

    @NotEmpty
    @NotBlank(message = "Instrumental name field shod not be empty!")
    @Length(max = 120, message = "Instrumental name shod have maximum of {max} characters!")
    private String instrumentalName;

    @NotEmpty
    @NotBlank(message = "Instrumental examination description field shod not be empty!")
    @Length(max = 250, message = "Instrumental examination description shod have maximum of {max} characters!")
    private String description;

}
