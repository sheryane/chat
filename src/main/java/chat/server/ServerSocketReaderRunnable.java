package chat.server;

import chat.ChatMessage;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLOutput;

public class ServerSocketReaderRunnable implements Runnable {
    private Socket client;
    private ChatLog chatLog;

    public ServerSocketReaderRunnable(Socket client, ChatLog chatLog) {
        this.client = client;
        this.chatLog = chatLog;

        try {
            chatLog.register(client);
        } catch (IOException e) {
            System.out.println("User cannot be registered due to connecion error.");
        }
    }

    @Override
    public void run() {

        /* {@link AutoCloseable} feature = do not
         * need to bother with closing stream after work is done. */

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(client.getInputStream())) {
            boolean connected = true;
            while (connected) {
                ChatMessage message = (ChatMessage) inputStream.readObject();
                if (message.getMessage().equalsIgnoreCase("exit")) {
                    connected = false;
                    System.out.println(message.getAuthor() + " disconnected.");
                    System.exit(0);
                    return;
                } else {
                    chatLog.acceptMessage(message);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Some client disconnected: " + e.getMessage());
        } finally {
            chatLog.unregister(client);
        }
    }
}
