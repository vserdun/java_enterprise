import lombok.Cleanup;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class MessageCreator {

    private static final String MESSAGE_FORMAT = ".txt";

    public static Queue<Message> createMessageQueue(String messageStoragePath) throws IOException {
        Queue<Message> messages = new ArrayDeque<>();
        File messageStorage = new File(messageStoragePath);
        if (messageStorage.exists() && messageStorage.isDirectory()) {
            for (File senderDir : Objects.requireNonNull(messageStorage.listFiles())) {
                for (File receiverDir : Objects.requireNonNull(senderDir.listFiles())) {
                    if(receiverDir.isDirectory()) {
                        messages.offer(createMessage(receiverDir));
                    }
                }
            }
        }
        return messages;
    }

    private static Message createMessage(File messageDir) throws IOException {
        String name = "", sender = "", receiver = "", text = "";
        Date creationTime = null;
        Map<String, byte[]> attachments  = new HashMap<>();

        //assume that there is always only one .txt file in the given directory and there are no additional folders;
        //all other files are considered as attachments
        for (File messageParam : Objects.requireNonNull(messageDir.listFiles())) {
            if (messageParam.getName().endsWith(MESSAGE_FORMAT)) {
                name = messageParam.getName();
                receiver = messageDir.getName();
                sender = (new File(messageDir.getParent())).getName();
                text = readMessageContent(messageParam);
                creationTime = new Date(messageParam.lastModified());
            } else {
                attachments.put(messageParam.getName(), readAttachmentContent(messageParam));
            }
        }
        return new Message(name, sender, receiver, text, creationTime, attachments);
    }

    private static byte[] readAttachmentContent(File attachment) throws IOException {
        return Files.readAllBytes(attachment.toPath());
    }

    private static String readMessageContent(File message) throws IOException {
        @Cleanup
        FileReader fileReader = new FileReader(message);
        @Cleanup
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String s;
        StringBuilder content = new StringBuilder();
        while ((s = bufferedReader.readLine()) != null) {
            content.append(s);
        }
        return content.toString();
    }

}
