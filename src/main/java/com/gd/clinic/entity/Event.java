package com.gd.clinic.entity;

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
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;

    @NonNull
    @Type(type = "uuid-char")
    private UUID prescriptionId;

    @NonNull
    @Type(type = "uuid-char")
    private UUID patientId;

    @NonNull
    private OffsetDateTime dateTime;

    @NonNull
    private Enum status;

    private String cancelReason;
}
