package messageExplorer;

import lombok.extern.slf4j.Slf4j;
import objects.Attachment;
import objects.Message;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Slf4j
public class MessageExplorerImpl implements MessageExplorer {
    private static final String MESSAGE_FORMAT = ".txt";

    @Override
    public Queue<Message> getMessages(String directoryName) {
        Queue<Message> messagesQueue = new LinkedList<>();
        try{
            File messagesDirectory = new File(directoryName);
            File[] senders = messagesDirectory.listFiles();
            if((senders == null) || senders.length == 0) {
                log.info("The main directory is empty. Nothing to send.");
                return messagesQueue;
            }
            for (File senderFile : senders) {
                File[] receivers = senderFile.listFiles();
                if(receivers != null){
                    if(receivers.length == 0) log.info("The sender " + senderFile.getName() + " has no receivers.");

                    for (File receiverFile : receivers) {
                        File[] messages = receiverFile.listFiles((directory, fileName) -> fileName.endsWith(MESSAGE_FORMAT));
                        if((messages == null) || (messages.length == 0)){
                            log.info("The sender " + senderFile.getName() + " has nothing to send to " + receiverFile.getName());
                        }else{
                            File message = messages[0];
                            String text = readMessage(message);
                            String subject = message.getName();

                            List<Attachment> attachments = new ArrayList<>();
                            for(File attachmentFile : Objects.requireNonNull(receiverFile.listFiles((directory, fileName) -> !fileName.endsWith(MESSAGE_FORMAT)))){
                                byte[] bytes = Files.readAllBytes(attachmentFile.toPath());
                                Attachment attachment = new Attachment(attachmentFile.getName(), bytes);
                                attachments.add(attachment);
                            }
                            Message messageObj = new Message();
                            messageObj.setCreationTime(new Date(message.lastModified()));
                            messageObj.setSender(senderFile.getName());
                            messageObj.setReceiver(receiverFile.getName());
                            messageObj.setName(subject.substring(0, subject.indexOf(MESSAGE_FORMAT)));
                            messageObj.setText(text);
                            messageObj.setAttachments(attachments);

                            messagesQueue.offer(messageObj);
                        }
                    }
                }
            }
        }catch (NullPointerException | IOException e){
            log.error(e.getMessage());
        }
        return messagesQueue;
    }

    private String readMessage(File fileName){
        char[] messageText = null;
        try(FileReader fileReader = new FileReader(fileName)){
            messageText = new char[(int) fileName.length()];
            fileReader.read(messageText);
        }catch (IOException e){
            log.error(e.getMessage());
        }
        if(messageText != null) return new String(messageText);

        return "";
    }
}

