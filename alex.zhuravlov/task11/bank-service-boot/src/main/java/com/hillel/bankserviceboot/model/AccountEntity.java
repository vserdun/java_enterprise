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
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @NotNull
    @Min(value = 0)
    @Column(name = "account_balance")
    @NonNull
    private double accountBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @NonNull
    private UserEntity user;

    @Column(name = "account_statement")
    private String accStatement = "";


    @ManyToMany(mappedBy = "accounts")

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<BankCard> cards;

    public void addAccStatement(String string) {
        accStatement.concat("\n" + string);
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "accountId=" + accountId +
                ", accountBalance=" + accountBalance +
                ", user=" + user.getUserId() +
                '}';
    }
}
