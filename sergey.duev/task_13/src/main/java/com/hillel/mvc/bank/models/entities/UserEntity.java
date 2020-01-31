package com.hillel.mvc.bank.models.entities;

import com.hillel.mvc.bank.models.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_last_name")
    private String lastName;

    @Column(name = "user_age")
    private int age;

    @Column(name = "user_gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_is_approve")
    private boolean isApprove;

    @ToString.Exclude
    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BankAccountEntity> bankAccountEntityList = new HashSet<>();

    @Embedded
    private Address address;

    @NonNull
    private String password;

    @Transient
    private String passwordConfirm;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = { CascadeType.ALL})
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<RoleEntity> roles = new HashSet<>();

    public void addBankAccount(BankAccountEntity bankAccountEntity) {
        bankAccountEntityList.add(bankAccountEntity);
        bankAccountEntity.setUserEntity(this);
    }

    public void removeBankAccount(BankAccountEntity bankAccountEntity) {
        bankAccountEntityList.remove(bankAccountEntity);
        bankAccountEntity.setUserEntity(null);
    }
}
