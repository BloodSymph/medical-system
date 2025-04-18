package com.company.patien.dto.admin;

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
public class PatientAdminDetailsResponse extends PatientAdminResponse {

    private List<AnalysisAdminResponse> analyses;

    private List<InstrumentalExaminationsAdminResponse> instrumentalExaminations;

}
