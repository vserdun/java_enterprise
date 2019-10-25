package objects;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Attachment {
    @ToString.Include(name = "file name")
    private String attachmentName;

    @ToString.Exclude
    private byte[] content;
}