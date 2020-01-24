package com.hillel.mvc.bank.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @Column(name = "card_amount")
    public double amount;

    @Column(name = "card_currency")
    public String currency;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "cards")
    private Set<BankAccountEntity> bankAccounts = new HashSet<>();
}
