import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServer {

    private static final int PORT = 12345;
    private static final AtomicInteger userIdCounter = new AtomicInteger(1); // Thread-safe ID counter
    public static CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        System.out.println("Chat Server has started...");
        System.out.println("You are welcome to my communication chat");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept(); // Accept new client
                String userId = "User" + userIdCounter.getAndIncrement(); // Generate unique ID

                // Create and start client handler
                ClientHandler clientHandler = new ClientHandler(socket, userId);
                clients.add(clientHandler);

                new Thread(clientHandler).start(); // Handle client on a new thread

                // Announce new user to all
                broadcast(userId + " has joined the chat.\n", clientHandler);
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    // Broadcast a message to all connected users (except sender)
    public static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message + "\n"); // Append newline for clear separation
            }
        }
    }

    // Remove a disconnected client and notify others
    public static void removeClient(ClientHandler client) {
        clients.remove(client);
        System.out.println(client.getUserId() + " has left the chat.");
        broadcast(client.getUserId() + " has left the chat.\n", client);
    }
}
