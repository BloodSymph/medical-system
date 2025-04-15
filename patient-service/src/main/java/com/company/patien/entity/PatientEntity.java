package com.company.patien.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Patient")
@Table(name = "patient")
@NamedEntityGraph(
        name = "patient-details-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("analysis"),
                @NamedAttributeNode("instrumentalExaminations")
})
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "user_email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 50)
    private String phoneNumber;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "patient")
    private List<AnalysisEntity> analysis;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "patient")
    private List<InstrumentalExaminationsEntity> instrumentalExaminations;

    @Version
    @Column(name = "patient_version", nullable = false, unique = true)
    private Long version;

}
