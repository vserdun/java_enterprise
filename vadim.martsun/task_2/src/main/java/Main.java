import messageExplorer.MessageExplorer;
import messageExplorer.MessageExplorerImpl;
import messageSender.MessageSender;
import messageSender.MessageSenderImpl;
import objects.Message;

import java.util.Queue;

public class Main {
    public static void main(String ... args){
        MessageExplorer messageExplorer = new MessageExplorerImpl();
        MessageSender messageSender = new MessageSenderImpl();

        Queue<Message> messages = messageExplorer.getMessages("task_2/messages");
        messageSender.sendMessages(messages);
    }
}
