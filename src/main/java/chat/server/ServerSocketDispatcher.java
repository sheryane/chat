package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketDispatcher {
    private ServerSocket server;
    private ChatLog chatLog;

    public ServerSocketDispatcher() {
        try {
            this.server = new ServerSocket(5568);
        } catch (IOException e) {
            System.out.println("Port is already occupied!");
            throw new RuntimeException(e);
        }
        chatLog = new ChatLog();
    }

    public void dispatchConnections() {
        try {
            while (true) {
                Socket client = server.accept();
                Runnable clientRunnable = new ServerSocketReaderRunnable(client, chatLog);
                new Thread(clientRunnable).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
