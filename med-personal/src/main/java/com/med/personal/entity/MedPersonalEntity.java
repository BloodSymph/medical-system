package com.med.personal.entity;

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
@Entity(name = "Med_Personal")
@Table(name = "med_personal")
public class MedPersonalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "med_personal_id")
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

    @Column(name = "specialty", nullable = false, length = 50)
    private String specialty;

    @Column(name = "spercialt_code", nullable = false, length = 50)
    private Long specialtyCode;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "med_version", nullable = false, unique = true)
    private Long version;

}
