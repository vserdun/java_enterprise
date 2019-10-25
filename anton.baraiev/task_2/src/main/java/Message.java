import lombok.*;
import java.util.*;

@Data
@AllArgsConstructor
public class Message {
    private String name;
    private String sender;
    private String receiver;
    private String text;
    private Date creationTime;
    private Map<String, byte[]> attachments;
}
