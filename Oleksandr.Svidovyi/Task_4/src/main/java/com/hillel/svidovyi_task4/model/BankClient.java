package com.hillel.svidovyi_task4.model;

import lombok.*;

@Data
@RequiredArgsConstructor
public class BankClient {

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Long ballance = 0L;
    @NonNull
    private Integer id;
    @NonNull
    private String firstName;
    @NonNull
    private String secondName;

}
