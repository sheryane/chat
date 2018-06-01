package chat.client;

public class ClientTerminalMain {
    public static void main(String[] args) {

        Runnable clientTerminal = new ClientTerminal();
        new Thread(clientTerminal).start();

    }
}
