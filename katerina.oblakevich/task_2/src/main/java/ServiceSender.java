import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

@Slf4j
public class ServiceSender {
    private Queue<Message> messageQueue;
    private static final String MAIN_PATH = new File("messages").getAbsolutePath();

    public ServiceSender() {
        messageQueue = new ArrayDeque<>();
    }

    public void getAllMessages() {
        int size = messageQueue.size();
        for (int i = 0; i < size; i++) {
            if (messageQueue.peek() != null) {
                log.info(getMessage(messageQueue.poll()));
            }
        }
    }

    public void fillMessagesFromFolders() throws IOException {
        for (File sender : getFilesInFolder(MAIN_PATH)) {
            for (File receiver : getFilesInFolder(MAIN_PATH + "/" + sender.getName())) {
                Message message = new Message();
                message.setSender(sender.getName());
                message.setReceiver(receiver.getName());
                Map<String, byte[]> map = new HashMap<>();
                for (File messageContent : getFilesInFolder(MAIN_PATH + "/" + sender.getName() + "/" +
                        receiver.getName())) {
                    if (getFileExtension(messageContent.getName()).equals("txt")) {
                        message.setName(nameWithoutExtension(messageContent));
                        message.setText(readMessageFromFile(messageContent));
                    } else {
                        map.put(nameWithoutExtension(messageContent), attachmentToByteArray(messageContent));
                        message.setAttachments(map);
                    }
                }
                messageQueue.offer(message);
            }
        }
    }

    private String getMessage(Message m) {
        StringBuilder result = new StringBuilder();
        result.append("Sending message ").append(m.getName());
        result.append(" from ").append(m.getSender());
        result.append(" to ").append(m.getReceiver());
        result.append(". Message content: ").append(m.getText());
        if (m.getAttachments() != null) {
            result.append(", attachments: ");
            for (String name : m.getAttachments().keySet()) {
                result.append(name).append(" ");
            }
        }
        return String.valueOf(result);
    }

    private String getFileExtension(String file) {
        String extension = "";
        int lastIndexOf = file.lastIndexOf(".");
        if (lastIndexOf >= 0) {
            extension = file.substring(lastIndexOf + 1);
        }
        return extension;
    }

    private File[] getFilesInFolder(String path) {
        return new File(path).listFiles();
    }

    private String nameWithoutExtension(File f) {
        int lastIndexOf = f.getName().lastIndexOf(".");
        if (lastIndexOf == -1)
            return f.getName();
        return f.getName().substring(0, lastIndexOf);
    }

    private byte[] attachmentToByteArray(File f) throws IOException {
        byte[] bytesArray = new byte[(int) f.length()];

        FileInputStream fis = new FileInputStream(f);
        fis.read(bytesArray); //read file into bytes[]
        fis.close();
        return bytesArray;
    }

    private String readMessageFromFile(File f) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        return bufferedReader.readLine();
    }
}
