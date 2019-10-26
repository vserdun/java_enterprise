import lombok.extern.java.Log;

import java.io.*;
import java.util.*;

@Log
public class MessagesQueue {
    public Deque loadMessages() {
        Deque<Message> messages = new ArrayDeque<>();
        File dir = new File(new File("messages").getAbsolutePath());

        String sender;
        String reciever;
        String name = "";
        String text = "";
        Date date = new Date();
        String attachmentName;
        Map<String, byte[]> attachments = new HashMap<>();
        byte[] attachment;

        if (dir.isDirectory()) {
            for (File senderDir : dir.listFiles()) {

                if (senderDir.isDirectory()) {
                    sender = senderDir.getName();
                    for (File receiverDir : senderDir.listFiles()) {
                        if (receiverDir.isDirectory()) {
                            reciever = receiverDir.getName();
                            attachments = new HashMap<>();
                            for (File receiverMess : receiverDir.listFiles()) {
                                if (receiverMess.isFile()) {
                                    if (extension(receiverMess).equals("txt")) {
                                        name = receiverMess.getName();
                                        text = readMessageText(receiverMess);
                                        date = new Date(receiverMess.lastModified());
                                    } else {
                                        attachmentName = receiverMess.getName();
                                        attachment = readMessageAttachment(receiverMess);
                                        attachments.put(attachmentName, attachment);
                                    }
                                }
                            }
                            messages.offer(new Message(date, sender, reciever, name, text, attachments));
                        }
                    }


                }
            }
        }


        return messages;
    }


    public String extension(File file) {
        String extension = "";

        int i = file.getName().lastIndexOf('.');
        if (i > 0) {
            extension = file.getName().substring(i + 1);
        }
        return extension;
    }

    public String readMessageText(File file) {
        String s = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            s = br.readLine();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        return s;
    }

    public byte[] readMessageAttachment(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] buffer = new byte[fileInputStream.available()];

            fileInputStream.read(buffer, 0, fileInputStream.available());
            return buffer;
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
            return null;
        }
    }
}
