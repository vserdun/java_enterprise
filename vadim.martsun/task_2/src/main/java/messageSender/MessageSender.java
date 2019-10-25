package messageSender;

import objects.Message;

import java.util.Queue;

public interface MessageSender {
    void sendMessages(Queue<Message> messages);

}
