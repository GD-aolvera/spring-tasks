package com.gd.clinic.security.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;

    @Column
    private String token;

    @Column
    private ZonedDateTime expiration;

    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

}
