import java.io.IOException;

public class Main2 {
    public static void main(String[] args) throws IOException {
        ServiceSender serviceSender = new ServiceSender();
        serviceSender.fillMessagesFromFolders();
        serviceSender.getAllMessages();
    }
}
