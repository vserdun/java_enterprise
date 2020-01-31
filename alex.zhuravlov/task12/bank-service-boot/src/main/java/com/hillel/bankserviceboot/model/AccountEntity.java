package com.hillel.bankserviceboot.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    @ToString.Include
    @EqualsAndHashCode.Include
    private int accountId;

    @NotNull
    @Min(value = 0)
    @Column(name = "account_balance")
    @ToString.Include
    @EqualsAndHashCode.Include
    private double accountBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private UserEntity user;

    @Column(name = "account_statement")
    private String accStatement = "";


    @ManyToMany(mappedBy = "accounts")
    private Set<BankCard> cards;

    public void addAccStatement(String string) {
        accStatement.concat("\n" + string);
    }

}
