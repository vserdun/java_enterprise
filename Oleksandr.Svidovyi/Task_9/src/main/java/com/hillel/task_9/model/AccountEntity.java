package com.hillel.task_9.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "accounts")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column
    @NonNull
    private long balance;

    @Column
    @NonNull
    private String currency;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ClientEntity client;
}
