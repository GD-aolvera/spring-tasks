package com.gd.clinic.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;


@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "patients")
public class Patient{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String diagnosis;

    @NonNull
    private String insuranceNumber;

    @Type(type = "uuid-char")
    //TODO: Uncomment when branch "security" is merged or vice versa to reference the user entity for the doctor id
    //@OneToOne
    //@JoinTable(name = "doctor_patients", joinColumns = @JoinColumn(name = "doctorId"), inverseJoinColumns = @JoinColumn(name = "patientId"))
    private UUID doctorId;

    private String status;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    private String createdBy;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Patient patient = (Patient) o;
        return id != null && Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
