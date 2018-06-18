package chat.client;

import chat.ChatMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTerminal implements Runnable {
    // Scanner will be used for reading user input
    private final Scanner scanner;
    // Socket is a connection to remote server.
    private final Socket socket;

    public ClientTerminal() {
        scanner = new Scanner(System.in);
        try {
            socket = new Socket("127.0.0.1", 5568);
        } catch (IOException e) {
            System.err.println("Could not connect to server: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        System.out.print("What is you nickname? ");
        String username = scanner.nextLine();
        String text;
        try (ObjectOutputStream serverStream =
                     new ObjectOutputStream(socket.getOutputStream())) {
            do {
                System.out.print("> ");
                text = scanner.nextLine();
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setAuthor(username);
                chatMessage.setMessage(text);
                serverStream.writeObject(chatMessage);
            } while (!text.equalsIgnoreCase("exit"));
        } catch (IOException e) {
            System.out.println("Connection lost.");
        }
        System.out.println("Bye!");
    }
}
