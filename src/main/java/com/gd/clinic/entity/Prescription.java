package com.gd.clinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gd.clinic.model.NewPrescriptionDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "pg-uuid")
    private UUID id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnoreProperties("prescriptionList")
    private Patient patient;

    @NonNull
    @Type(type = "uuid-char")
    @OneToOne
    @JoinColumn(name = "treatmentId")
    private Treatment treatment;

    @NonNull
    private String timePattern;

    @NonNull
    private Integer period;

    @OneToMany(fetch = FetchType.EAGER)
    @Transient
    private Set<Event> eventList =  new HashSet<>();

    private OffsetDateTime datePrescribed;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('ACTIVE', 'INACTIVE')")
    private NewPrescriptionDto.StatusEnum status;

}