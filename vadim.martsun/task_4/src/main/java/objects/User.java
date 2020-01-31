package objects;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private Gender gender;
}