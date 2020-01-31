import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class Message {
    private Date creationTime;
    private String sender;
    private String receiver;
    private String name;
    private String text;
    private Map<String, byte[]> attachments;
}
