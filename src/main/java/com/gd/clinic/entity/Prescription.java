package com.gd.clinic.entity;

import com.gd.clinic.enums.DaysEnum;
import com.gd.clinic.model.NewPrescriptionDto;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.type.StringType;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.*;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Type(type = "uuid-char")
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "treatmentId")
    private Treatment treatment;

    private Integer frequency;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('DAY', 'WEEK', 'MONTH')")
    private NewPrescriptionDto.FrequencyUnitEnum frequencyUnit;

    private String description;

    @ElementCollection(targetClass = DaysEnum.class)
    @CollectionTable(name = "prescription_days_of_the_week", joinColumns = @JoinColumn(name = "prescription_id", referencedColumnName = "id"))
    @Enumerated(EnumType.STRING)
    private List<DaysEnum> daysOfTheWeek;

    @OneToMany(fetch = FetchType.EAGER)
    @Transient
    private Set<Event> eventList =  new HashSet<>();

    private OffsetDateTime datePrescribed;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('ACTIVE', 'INACTIVE')")
    private NewPrescriptionDto.StatusEnum status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Prescription prescription = (Prescription) o;
        return id != null && Objects.equals(id, prescription.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}