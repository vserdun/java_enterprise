import lombok.extern.java.Log;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Queue;

@Log
public class SimpleMessanger {
    public void sendMessages() {
        MessagesQueue messagesQueue = new MessagesQueue();
        Deque<Message> messages = messagesQueue.loadMessages();
        String msgCount = "Numbers of messages ";

        log.info(msgCount + messages.size());

        for (Message message : messages
        ) {
            StringBuffer sb = new StringBuffer();
            sb.append("Sending message ")
                    .append(message.getName())
                    .append(" from ")
                    .append(message.getSender())
                    .append(" to ")
                    .append(message.getReceiver())
                    .append(". Message content: ")
                    .append(message.getText())
                    .append(", attachments: ");
            for (Map.Entry entry: message.getAttachments().entrySet()
                 ) {
                sb.append(entry.getKey());
                sb.append(" ");
            }
            log.info(sb.toString());
            messages.pop();
            log.info(msgCount + messages.size());
        }
    }
}
