package com.gd.clinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;

    @NonNull
    @ManyToOne
    @JoinTable(name = "patient_prescriptions", joinColumns = @JoinColumn(name = "prescriptionId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "patientId", referencedColumnName = "id"))
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
    private Set<Event> eventList =  new HashSet<>();

    private OffsetDateTime datePrescribed;

}