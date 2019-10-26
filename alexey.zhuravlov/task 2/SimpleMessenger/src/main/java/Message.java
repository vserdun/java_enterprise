import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Message {
    private Date creationTime;
    private String sender;
    private String receiver;
    private String name;
    private String text;
    private Map<String, byte[]> attachments;


}
