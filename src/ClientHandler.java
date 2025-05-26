import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private final String userId;

    public ClientHandler(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;

        DataInputStream tempIn = null;
        DataOutputStream tempOut = null;

        try {
            tempIn = new DataInputStream(socket.getInputStream());
            tempOut = new DataOutputStream(socket.getOutputStream());

            // Send welcome message to user
            tempOut.writeUTF("Welcome " + userId + "! You can start chatting.\n");
            tempOut.flush();

            // Notify other users
            ChatServer.broadcast(userId + " joined the chat.\n", this);
        } catch (IOException e) {
            System.err.println("Error setting up client: " + e.getMessage());
        }

        this.in = tempIn;
        this.out = tempOut;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readUTF()) != null) {
                String formattedMessage = userId + ": " + message + "\n";
                System.out.print(formattedMessage); // Server console log
                ChatServer.broadcast(formattedMessage, this); // Send to all other clients
            }
        } catch (IOException e) {
            System.out.println(userId + " disconnected.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket for " + userId);
            }

            // Remove user and inform others
            ChatServer.removeClient(this);
            ChatServer.broadcast(userId + " has left the chat.\n", this);
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            System.err.println("Failed to send message to " + userId);
        }
    }
}
