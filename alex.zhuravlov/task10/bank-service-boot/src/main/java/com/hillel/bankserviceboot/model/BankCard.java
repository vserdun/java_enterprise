package com.hillel.bankserviceboot.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "cards")
@NoArgsConstructor
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int cardId;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "card_id")},
            inverseJoinColumns = {@JoinColumn(name = "account_id")})
    private Set<AccountEntity> accounts;
}
