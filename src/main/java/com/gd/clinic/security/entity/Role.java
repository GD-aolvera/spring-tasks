package com.gd.clinic.security.entity;

import com.gd.clinic.security.enums.RoleName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;
    @NonNull
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

}
