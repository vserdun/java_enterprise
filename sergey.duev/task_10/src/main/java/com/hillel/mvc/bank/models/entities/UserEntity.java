package com.hillel.mvc.bank.models.entities;

import com.hillel.mvc.bank.models.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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

    public void addBankAccount(BankAccountEntity bankAccountEntity) {
        bankAccountEntityList.add(bankAccountEntity);
        bankAccountEntity.setUserEntity(this);
    }

    public void removeBankAccount(BankAccountEntity bankAccountEntity) {
        bankAccountEntityList.remove(bankAccountEntity);
        bankAccountEntity.setUserEntity(null);
    }
}
