package com.hillel.mvc.bank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name = "user_name")
    @NotBlank(message = "Name must be not blank")
    private String name;
    @Column(name = "user_last_name")
    @NotBlank(message = "Last name must be not blank")
    private String lastName;
    @Column(name = "user_age")
    @Min(message = "Age must be more then 18", value = 18)
    private int age;
    @NotNull(message = "Gender must be not blank")
    @Column(name = "user_gender")
    private Gender gender;
    @Email(message = "Email must be valid")
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_is_approve")
    private boolean isApprove;

}
