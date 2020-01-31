import com.google.common.io.Files;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

@Slf4j
public class Main {

    private static final String SENDING_LOG_FORMAT = "Sending message {} from {} to {}. Message content: {}, attacments: {}";
    private static final String START_LOG_FORMAT = "Start sending...";
    private static final String STOP_LOG_FORMAT = "Stop sending...";
    private static final String MESSAGE_EXT = "txt";

    public static void main(String[] args) {
        Queue<Message> messages = prepareMessages(args[0]);
        sendMessages(messages);
    }

    public static Queue<Message> prepareMessages(String dirPath){
        Queue<Message> messages = new ArrayDeque<>();
        File file = new File(dirPath);
        if (file.exists()) {
            File[] senders = file.listFiles();
            if (senders != null) {
                for (File sender : senders) {
                    File[] receivers = sender.listFiles();
                    if (receivers != null) {
                        for (File receiver : receivers) {
                            Message.MessageBuilder builder = Message.builder()
                                    .sender(sender.getName())
                                    .creationTime(receiver.lastModified())
                                    .receiver(receiver.getName());
                            File[] attachments = receiver.listFiles();
                            if (attachments != null) {
                                for (File attachment : attachments) {
                                    String extension = Files.getFileExtension(attachment.getName());
                                    if (extension != null && extension.equals(MESSAGE_EXT)) {
                                        builder.name(Files.getNameWithoutExtension(attachment.getName()));
                                        try {
                                            builder.text(readTextFile(attachment));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        try {
                                            builder.attachment(attachment.getName(), readByteFile(attachment));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                            messages.add(builder.build());
                        }
                    }
                }
            }
        }
        return messages;
    }

    public static String readTextFile(File file) throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        @Cleanup FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    public static byte[] readByteFile(File file) throws IOException{
        byte[] result = new byte[(int) file.length()];
        @Cleanup InputStream ios = new FileInputStream(file);
        ios.read(result);
        return result;
    }

    public static void sendMessages(Queue<Message> messages) {
        log(START_LOG_FORMAT);
        sendMessage(messages);
        log(STOP_LOG_FORMAT);
    }

    public static void sendMessage(Queue<Message> messages) {
        Message message = messages.poll();
        if (message != null) {
            log(message);
            sendMessage(messages);
        }
    }

    private static void log(Message message) {
        log.info(SENDING_LOG_FORMAT, message.getName(), message.getSender(), message.getReceiver(), message.getText(), message.getAttachments().keySet());
    }

    private static void log(String msg) {
        log.info(msg);
    }
}
