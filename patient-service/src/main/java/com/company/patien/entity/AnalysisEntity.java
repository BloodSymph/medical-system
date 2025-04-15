package com.company.patien.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Analysis")
@Table(name = "analysis")
public class AnalysisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "analysis_id")
    private Long id;

    @Column(name = "analysis_name", nullable = false, length = 50)
    private String analysisName;

    @Column(name = "analysis_description", nullable = false, length = 120)
    private String description;

    @Version
    @Column(name = "analysis_version", nullable = false, unique = true)
    private Long version;

}
