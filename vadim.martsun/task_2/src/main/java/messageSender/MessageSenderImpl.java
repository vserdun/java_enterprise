package messageSender;

import lombok.extern.slf4j.Slf4j;
import objects.Message;

import java.util.Queue;

@Slf4j
public class MessageSenderImpl implements MessageSender{

    public void sendMessages(Queue<Message> messages){
        int counter = 1;
        while (!messages.isEmpty()){
            log.info("Message " + counter + ". Left: " + (messages.size() - 1));
            sendMessage(messages.poll());
            counter++;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

    private void sendMessage(Message message){
        log.info("Sending message " + message.getName()
                + " from " + message.getSender()
                + " to " + message.getReceiver()
                + "\nMessage content: " +message.getText()
                + "\nAttachments: " + ((message.getAttachments().size() != 0)
                                        ? message.getAttachments()
                                        : "no attachments"));
    }
}
