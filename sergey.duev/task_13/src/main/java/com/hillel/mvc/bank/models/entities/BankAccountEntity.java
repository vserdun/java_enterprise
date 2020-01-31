package com.hillel.mvc.bank.models.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bank_accounts")
public class BankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_account_id")
    private long id;

    @NonNull
    @Column(name = "bank_account_created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "bank_account_balance")
    private double balance;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = { CascadeType.ALL})
    @JoinTable(
            name = "bank_account_cards",
            joinColumns = { @JoinColumn(name = "bank_account_id") },
            inverseJoinColumns = { @JoinColumn(name = "card_id") }
    )
    private Set<CardEntity> cards = new HashSet<>();

    public void addCard(CardEntity cardEntity) {
        getCards().add(cardEntity);
        cardEntity.getBankAccounts().add(this);
    }

    public void removeCard(CardEntity cardEntity) {
        getCards().remove(cardEntity);
        cardEntity.getBankAccounts().remove(this);
    }
}
