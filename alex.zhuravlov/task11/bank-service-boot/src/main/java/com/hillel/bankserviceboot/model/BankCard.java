package com.hillel.bankserviceboot.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int cardId;

    @Column(name = "card_name")
    private String cardName;


    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "card_id")},
            inverseJoinColumns = {@JoinColumn(name = "account_id")})

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<AccountEntity> accounts;
}
