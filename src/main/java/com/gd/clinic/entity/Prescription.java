package com.gd.clinic.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;

    @NonNull
    @Type(type = "uuid-char")
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @NonNull
    @Type(type = "uuid-char")
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "treatmentId")
    private Treatment treatment;

    @NonNull
    private String timePattern;

    @NonNull
    private Integer period;

    private OffsetDateTime datePrescribed;

}