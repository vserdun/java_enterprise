package messageExplorer;

import objects.Message;

import java.util.Queue;

public interface MessageExplorer {
    Queue<Message> getMessages(String directoryName);
}
