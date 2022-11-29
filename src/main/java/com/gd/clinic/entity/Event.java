package com.gd.clinic.entity;

import com.gd.clinic.model.NewEventDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "pg-uuid")
    private UUID id;

    @NonNull
    @ManyToOne
   // @JoinTable(name = "prescriptions_event_list", joinColumns = @JoinColumn(name = "eventId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "prescriptionId", referencedColumnName = "id"))
    private Prescription prescription;

    @NonNull
    private OffsetDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('SCHEDULED', 'DONE')")
    private NewEventDto.StatusEnum status;

    private String cancelReason;
}
