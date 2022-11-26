package com.gd.clinic.entity;

import com.gd.clinic.model.NewEventDto;
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
    @ManyToOne
    @JoinTable(name = "prescription_events", joinColumns = @JoinColumn(name = "eventId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "prescriptionId", referencedColumnName = "id"))
    private Prescription prescription;

    @NonNull
    private OffsetDateTime dateTime;

    @NonNull
    private NewEventDto.StatusEnum status;


    private String cancelReason;
}
