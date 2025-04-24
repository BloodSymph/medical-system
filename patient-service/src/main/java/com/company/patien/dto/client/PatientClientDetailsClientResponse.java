package com.company.patien.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PatientClientDetailsClientResponse extends PatientClientResponse {

    private List<AnalysisResponse> analyses;

    private List<InstrumentalExaminationsResponse> instrumentalExaminations;

}
