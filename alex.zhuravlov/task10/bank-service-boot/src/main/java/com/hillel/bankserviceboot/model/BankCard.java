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

    @ManyToMany(mappedBy = "cards")

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<AccountEntity> accounts;
}
