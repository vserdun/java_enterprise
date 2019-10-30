import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Report {

    private int id;
    private LocalDate date;
    private String name;
    private String description;
    private Status status;
}
