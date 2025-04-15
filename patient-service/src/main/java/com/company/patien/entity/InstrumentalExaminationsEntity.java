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
@Entity(name = "Instrumental")
@Table(name = "instruamental")
public class InstrumentalExaminationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instrumental_if")
    private Long id;

    @Column(name = "instrumental_name", nullable = false, length = 50)
    private String  instrumentalName;

    @Column(name = "instrumental_description", nullable = false, length = 120)
    private String description;

    @Version
    @Column(name = "instrumental_version", nullable = false, unique = true)
    private Long version;

}
