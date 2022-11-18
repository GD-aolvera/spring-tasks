package com.gd.clinic.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;

    @NonNull
    @Type(type = "uuid-char")
    private UUID patientId;

    @NonNull
    @Type(type = "uuid-char")
    private UUID treatmentId;

    @NonNull
    String timePattern;

    @NonNull
    Integer period;

    OffsetDateTime datePrescribed = OffsetDateTime.now();

}