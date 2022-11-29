package com.gd.clinic.entity;

import com.gd.clinic.model.NewTreatmentDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@RequiredArgsConstructor
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

    @NonNull
    private String name;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "ENUM('PROCEDURE', 'MEDICINE')")
    private NewTreatmentDto.TypeEnum type;

}