package com.hillel.bankserviceboot.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    private int userId;

    @NotBlank
    @NonNull
    @Column(name = "first_name")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String firstName;

    @NotBlank
    @NonNull
    @Column(name = "last_name")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String lastName;

    @NonNull
    @Column(name = "email", unique = true)
    private String email;

    @Embedded
    private AddressEntity address;

    @NonNull
    private String password;

    @Transient
    private String passwordConfirm;

    @OneToMany(mappedBy = "user")
    private List<AccountEntity> accountEntityList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<RoleEntity> roles;
}
