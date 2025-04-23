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
public class AnalysisRequest {

    @NotEmpty
    @NotBlank(message = "Analysis name field shod not be empty!")
    @Length(max = 120, message = "Analysis name shod have maximum of {max} characters!")
    private String analysisName;

    @NotEmpty
    @NotBlank(message = "Analysis description field shod not be empty!")
    @Length(max = 250, message = "Analysis description shod have maximum of {max} characters!")
    private String description;

}
