import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class Report {

    @NonNull
    private long id;
    @NonNull
    private LocalDate date;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private Status status;

    public enum Status{
        GREEN,
        YELLOW,
        RED
    };
}
