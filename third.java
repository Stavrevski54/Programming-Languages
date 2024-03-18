// Assignment 3: Simple chat application
// Develop a simple chat application using Java's networking capabilities, specifically Socket and ServerSocket, along with threading to handle multiple clients.

// SERVER CLASS
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 12345;
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                // Accept incoming client connections
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                // Create a new thread for each client
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("Error in the server: " + e.getMessage());
        }
    }

    // Broadcast message to all connected clients
    public static synchronized void broadcast(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }

    // ClientHandler class to handle communication with each client
    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter writer;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                // Set up input and output streams for communication with client
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);

                // Add client's PrintWriter to the set
                synchronized (clientWriters) {
                    clientWriters.add(writer);
                }

                // Display client's IP address and dummy URL
                InetAddress clientAddress = clientSocket.getInetAddress();
                System.out.println("Client IP: " + clientAddress.getHostAddress());
                System.out.println("Dummy URL: http://" + clientAddress.getHostAddress() + "/chat");

                // Read and broadcast messages from client
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Message received from client: " + message);
                    broadcast(message);
                }
            } catch (IOException e) {
                System.err.println("Error handling client: " + e.getMessage());
            } finally {
                // Remove client's PrintWriter from the set upon disconnection
                if (writer != null) {
                    synchronized (clientWriters) {
                        clientWriters.remove(writer);
                    }
                }
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error closing client socket: " + e.getMessage());
                }
            }
        }
    }
}

// CLIENT CLASS
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter serverWriter = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            // Start a thread to read messages from the server
            Thread readThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = serverReader.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.err.println("Error reading from server: " + e.getMessage());
                }
            });
            readThread.start();

            // Read and send messages to the server
            String message;
            while (true) {
                message = scanner.nextLine();
                serverWriter.println(message);
            }
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}
