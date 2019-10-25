import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.Map;

@Data
@Builder
public class Message {

    private long creationTime;
    private String sender;
    private String receiver;
    private String name;
    private String text;
    @Singular
    private Map<String, byte[]> attachments;
}
