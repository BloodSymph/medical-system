package com.company.patien.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstrumentalExaminationsResponse {

    private Long id;

    @JsonProperty("instrumental_name")
    private String  instrumentalName;

    private String description;

}
