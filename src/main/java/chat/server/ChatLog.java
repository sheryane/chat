package chat.server;

import chat.ChatMessage;
import chat.DatedChatMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatLog {
    private Map<Socket, ObjectOutputStream> clients;

    public ChatLog() {
        clients = new ConcurrentHashMap<>();
    }

    public void acceptMessage(ChatMessage chatMessage) {
        DatedChatMessage datedMessage = new DatedChatMessage(chatMessage);
        printMessage(datedMessage);
        updateClients(datedMessage);
    }

    private void printMessage(DatedChatMessage datedMessage) {
        StringBuilder messageBuilder = new StringBuilder();
        System.out.println(messageBuilder
                .append(datedMessage.getReceiveDate()).append(" ")
                .append(datedMessage.getAuthor()).append(": ")
                .append(datedMessage.getMessage()));
    }

    private void updateClients(DatedChatMessage datedMessage) {
        clients.values().iterator().forEachRemaining((clientConnection) -> {
            try {
                clientConnection.writeObject(datedMessage);
            } catch (IOException e) {
                System.out.println("Could not send message");
            }
        });
    }

    public void register(Socket client) throws IOException {
        clients.put(client, new ObjectOutputStream(client.getOutputStream()));
    }

    public void unregister(Socket client) {
        ObjectOutputStream connection = clients.remove(client);
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException e) {

            }
        }
    }
}
