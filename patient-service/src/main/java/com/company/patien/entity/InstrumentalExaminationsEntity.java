package com.company.patien.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private PatientEntity patient;

    @Version
    @Column(name = "instrumental_version", nullable = false, unique = true)
    private Long version;

}
