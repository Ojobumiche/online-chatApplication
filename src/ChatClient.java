import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in)
        ) {
            // Start a thread to listen for incoming messages from the server
            Thread readerThread = new Thread(() -> {
                try {
                    while (true) {
                        String response = in.readUTF();
                        System.out.println(response); // Always prints each message on a new line
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });
            readerThread.setDaemon(true); // Will not prevent program exit
            readerThread.start();

            // Main thread: read user input and send to server
            while (true) {
                String message = scanner.nextLine().trim();

                // Optional: exit command
                if (message.equalsIgnoreCase("/exit")) {
                    System.out.println("Exiting chat...");
                    break;
                }

                if (!message.isEmpty()) {
                    out.writeUTF(message);
                    out.flush(); // Make sure the message is sent immediately
                }
            }

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
