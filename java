BulettinBoardServer.java
  import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class BulletinBoardServer {
    private static final int PORT = 5000;
    private static final String FILE_NAME = "bulletin_board.txt";

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection from: " + clientSocket.getInetAddress().getHostAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized List<String> readMessages() {
        List<String> messages = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                messages.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public static synchronized boolean addMessage(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(message);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

ClientHandler.java
  import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if ("READ_MESSAGES".equals(inputLine)) {
                    List<String> messages = BulletinBoardServer.readMessages();
                    for (String message : messages) {
                        out.println(message);
                    }
                    out.println("EOF");
                } else if (inputLine.startsWith("POST_MESSAGE:")) {
                    String messageContent = inputLine.substring("POST_MESSAGE:".length());
                    boolean success = BulletinBoardServer.addMessage(messageContent);
                    if (success) {
                        out.println("POST_SUCCESS");
                    } else {
                        out.println("POST_FAILED");
                    }
                } else if ("QUIT".equals(inputLine)) {
                    break;
                } else {
                    out.println("Invalid request");
                }
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


BulettinBoardClient.java
  import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BulletinBoardClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in)
        ) {
            String userInput;
            while (true) {
                System.out.println("Enter '1' to read messages, '2' to post a message, or 'QUIT' to exit:");
                userInput = scanner.nextLine();
                if ("1".equals(userInput)) {
                    out.println("READ_MESSAGES");
                    String response;
                    while (!(response = in.readLine()).equals("EOF")) {
                        System.out.println(response);
                    }
                } else if ("2".equals(userInput)) {
                    System.out.println("Enter your message:");
                    String message = scanner.nextLine();
                    out.println("POST_MESSAGE:" + message);
                    String response = in.readLine();
                    System.out.println(response);
                } else if ("QUIT".equals(userInput)) {
                    out.println("QUIT");
                    break;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
