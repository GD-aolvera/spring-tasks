package com.gd.clinic.entity;

import com.gd.clinic.model.NewTreatmentDto;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "treatments")
public class Treatment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "pg-uuid")
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "ENUM('PROCEDURE', 'MEDICINE')")
    private NewTreatmentDto.TypeEnum type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Treatment treatment = (Treatment) o;
        return id != null && Objects.equals(id, treatment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}