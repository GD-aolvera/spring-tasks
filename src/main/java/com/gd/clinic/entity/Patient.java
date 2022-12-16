package com.gd.clinic.entity;

import com.gd.clinic.model.NewPatientDto;
import com.gd.clinic.security.entity.User;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "pg-uuid")
    private UUID id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String diagnosis;

    @NonNull
    private String insuranceNumber;

    @NonNull
    private OffsetDateTime birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctorId")
    private User doctorId;

    @OneToMany(fetch = FetchType.EAGER)
    @Transient
    private Set<Prescription> prescriptionList = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('IN_TREATMENT', 'RECOVERED')")
    private NewPatientDto.StatusEnum status;

    private OffsetDateTime createdAt;

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
