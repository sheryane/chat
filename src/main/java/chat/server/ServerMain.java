package chat.server;

public class ServerMain {
    public static void main(String[] args) {
        new ServerSocketDispatcher().dispatchConnections();
    }
}
