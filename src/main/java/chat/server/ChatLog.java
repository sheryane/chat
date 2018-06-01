package chat.server;

import chat.ChatMessage;
import chat.DatedChatMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatLog {
    private List<Socket> clients;

    public ChatLog() {
        clients = new ArrayList<>();
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
        for (Socket client : clients) {
            try (ObjectOutputStream clientConnection =
                         new ObjectOutputStream(client.getOutputStream())) {
                clientConnection.writeObject(datedMessage);
            } catch (IOException e) {
                System.out.println("Could not send message");
            }
        }
    }

    public void register(Socket client) {
        clients.add(client);
    }

    public void unregister(Socket client) {
        clients.remove(client);
    }

}
