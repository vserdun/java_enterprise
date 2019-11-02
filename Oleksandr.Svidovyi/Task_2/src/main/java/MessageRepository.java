import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MessageRepository {
    final static Logger log = Logger.getLogger(MessageRepository.class);

    ArrayDeque<Message> allMessages = new ArrayDeque<>();

    public ArrayDeque<Message> getAllMessages(File folder) {
        File[] folderEntries = folder.listFiles();

        if (folderEntries != null) {
            for (File entry : folderEntries) {
                if (entry.isDirectory()) {
                    getAllMessages(entry);
                    continue;
                }
                if (entry.getAbsolutePath().endsWith(".txt")) {
                    try {
                        allMessages.add(mapMessage(entry));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else break;
            }
        }

        return allMessages;
    }

    private Message mapMessage(File entry) throws IOException {
        Message returnValue = new Message();
        ArrayList<byte[]> attachments;
        FileTime creationTime = FileTime.from(0111L, TimeUnit.SECONDS);
        String sender;
        String receiver;
        String name;

        if (entry != null) {
            String messagePath = entry.getAbsolutePath().replace(System.getProperty("user.dir") + "\\messages\\", "");
            String[] messageAtributes = messagePath.split("\\\\");
            sender = messageAtributes[0];
            receiver = messageAtributes[1];
            name = messageAtributes[2].replace(".txt", "");
            try {
                BasicFileAttributes attrs = Files.readAttributes(Paths.get(entry.getAbsolutePath()), BasicFileAttributes.class);
                creationTime = attrs.creationTime();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Message message = new Message(sender, receiver, name);
            message.setCreationTime(creationTime);

            BufferedReader bufferedReader = new BufferedReader(new FileReader(entry));

            String messageText;
            StringBuilder sb = new StringBuilder();
            while ((messageText = bufferedReader.readLine()) != null) {
                sb.append(messageText);
            }
            message.setText(sb.toString());

            message.setAttachments(getMessageAttachments(entry.getAbsolutePath().replace(message.getName() + ".txt", "")));

            returnValue = message;
        }

        log.info(returnValue.toString());
        return returnValue;
    }

    private ArrayList<byte[]> getMessageAttachments(String path) {
        ArrayList<byte[]> returnvalue = new ArrayList<>();

        File[] files = new File(path).listFiles();

        for (int i = 0; i < files.length; i++) {
            if (!files[i].getName().endsWith(".txt")) {
                try {
                    byte[] fileContent = Files.readAllBytes(files[i].toPath());
                    returnvalue.add(fileContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return returnvalue;
    }


}
