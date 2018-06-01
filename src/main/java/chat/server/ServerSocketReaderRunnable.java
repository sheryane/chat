package chat.server;

import chat.ChatMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerSocketReaderRunnable implements Runnable {
    private Socket client;
    private ChatLog chatLog;

    public ServerSocketReaderRunnable(Socket socket, ChatLog chatLog) {
        this.client = socket;
        this.chatLog = chatLog;
        chatLog.register(socket);
    }

    @Override
    public void run() {
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(client.getInputStream())) {
            boolean connected = true;
            while (connected) {
                ChatMessage message = (ChatMessage) inputStream.readObject();
                if (message.getMessage().equalsIgnoreCase("exit")) {
                    connected = false;
                    System.out.println(message.getAuthor() + " disconnected.");
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
