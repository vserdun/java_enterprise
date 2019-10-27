package objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class Report {
    private int id;
    private LocalDate date;
    private String name;
    private String description;
    private Status status;
}
