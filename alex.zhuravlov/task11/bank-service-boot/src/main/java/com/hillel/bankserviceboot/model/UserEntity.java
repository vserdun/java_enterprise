package com.hillel.bankserviceboot.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @NotBlank
    @NonNull
    @Column(name = "first_name")
    private String userFirstName;

    @NotBlank
    @NonNull
    @Column(name = "last_name")
    private String userLastName;

    @Embedded
    private AddressEntity address;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<AccountEntity> accountEntityList;
}
