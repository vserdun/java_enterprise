package objects;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Message {

    private Date creationTime;
    private String sender;
    private String receiver;

    @ToString.Include(name = "subject")
    private String name;
    private String text;

    @ToString.Exclude
    private List<Attachment> attachments;
}