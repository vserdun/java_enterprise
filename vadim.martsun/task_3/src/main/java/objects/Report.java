package objects;

import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class Report {
    private int id;
    private LocalDate date;
    private String name;
    private String description;
    private Status status;
}
