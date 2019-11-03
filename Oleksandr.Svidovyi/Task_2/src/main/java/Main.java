import java.io.File;
import java.util.ArrayDeque;


public class Main {


    public static void main(String[] args) {
        String targetFolfer = System.getProperty("user.dir") + "\\" + "messages";

        MessageRepository messageRepository = new MessageRepository();
        ArrayDeque<Message> allMessages = messageRepository.getAllMessages(new File(targetFolfer));

    }
}
