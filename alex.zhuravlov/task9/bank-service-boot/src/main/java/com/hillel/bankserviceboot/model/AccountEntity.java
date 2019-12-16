package com.hillel.bankserviceboot.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @NotNull
    @Min(value = 0)
    @Column(name = "account_balance")
    private double accountBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "account_statement")
    private String accStatement = "";


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
