import lombok.extern.slf4j.Slf4j;

import java.util.Queue;

@Slf4j
public class MessageSender {

    public static void main(String[] args) {
        try {
            log.info("Message queue creating started..");
            Queue<Message> messages = MessageCreator.createMessageQueue(".\\src\\messages");
            log.info("Messages have been created!");
            log.info("Message sending started..");
            sendMessages(messages);
            log.info("All messages have been sent successfully!");
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private static void sendMessages(Queue<Message> messages) {
        while(!messages.isEmpty()) {
            log.info(messages.poll() + " has been sent");
        }
    }
}
