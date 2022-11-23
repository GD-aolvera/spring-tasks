package com.gd.clinic.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
// TODO: Uncomment once security has been merged to this branch or vice versa
//import org.springframework.security.core.context.SecurityContextHolder;

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
    private UUID doctorId;

    private String status;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    //TODO: Uncomment once branch "security" has been merged to this branch or vice versa
    private String createdBy/*=getCurrentUser()*/;


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
    //TODO: Uncomment once branch "security" has been merged to this branch or vice versa
   /* private String getCurrentUser() {
        String prince = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return prince.substring(prince.indexOf("userName=") + 9, prince.indexOf(", pass"));
    } */
}
