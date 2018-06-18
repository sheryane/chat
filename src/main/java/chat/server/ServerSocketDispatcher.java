package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketDispatcher {
    private ServerSocket server;
    private ChatLog chatLog;

    public ServerSocketDispatcher() {
        try {
            //this server will accept connections on port 5568
            this.server = new ServerSocket(5568);
        } catch (IOException e) {
            System.out.println("Port is already occupied!");
            throw new RuntimeException(e);
        }
        chatLog = new ChatLog();
    }

    public void dispatchConnections() {
        try {
            System.out.println("Server started!");
            while (true) {
                Socket client = server.accept();
                System.out.println("Got new chat client!");
                Runnable clientRunnable = new ServerSocketReaderRunnable(client, chatLog);
                new Thread(clientRunnable).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
