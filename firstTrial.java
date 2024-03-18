// Assignment 1: File Management System
// Implement a Java application "FileManager" that manages files and directories. The application should provide functionalities to create, 
// delete, and list files and directories. Additionally, the application should allow reading from and writing to files.

import java.io.*;

public class FileManager {
    public static void main(String[] args) {
        // Check if the number of arguments is valid
        if (args.length < 2 || args.length > 3) {
            System.out.println("Usage: java FileManager <command> <path> [content]");
            return;
        }

        // Extract the command from the first argument
        String command = args[0];

        // Extract the path from the second argument
        String path = args[1];

        // Execute the appropriate command
        switch (command) {
            case "create-file":
                createFile(path);
                break;
            case "delete-file":
                deleteFile(path);
                break;
            case "create-dir":
                createDirectory(path);
                break;
            case "delete-dir":
                deleteDirectory(path);
                break;
            case "list":
                listFilesAndDirectories(path);
                break;
            case "write-file":
                // Check if content argument is provided
                if (args.length != 3) {
                    System.out.println("Usage: java FileManager write-file <path> <content>");
                    return;
                }
                String content = args[2];
                writeFile(path, content);
                break;
            case "read-file":
                readFile(path);
                break;
            default:
                System.out.println("Invalid command.");
        }
    }

    // Method to create a new file
    private static void createFile(String path) {
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    // Method to delete a file
    private static void deleteFile(String path) {
        File file = new File(path);
        if (file.delete()) {
            System.out.println("File deleted successfully.");
        } else {
            System.out.println("Failed to delete file. Check if the file exists and you have proper permissions.");
        }
    }

    // Method to create a new directory
    private static void createDirectory(String path) {
        File directory = new File(path);
        if (directory.mkdir()) {
            System.out.println("Directory created successfully.");
        } else {
            System.out.println("Failed to create directory.");
        }
    }

    // Method to delete a directory
    private static void deleteDirectory(String path) {
        File directory = new File(path);
        if (directory.isDirectory()) {
            if (directory.list().length == 0) {
                if (directory.delete()) {
                    System.out.println("Directory deleted successfully.");
                } else {
                    System.out.println("Failed to delete directory.");
                }
            } else {
                System.out.println("Directory is not empty. Cannot delete.");
            }
        } else {
            System.out.println("Path does not point to a directory.");
        }
    }

    // Method to list files and directories
    private static void listFilesAndDirectories(String path) {
        File directory = new File(path);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            System.out.println("Files and directories in " + path + ":");
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("Path does not point to a directory.");
        }
    }

    // Method to write content to a file
    private static void writeFile(String path, String content) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(content);
            System.out.println("Content written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read content from a file
    private static void readFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            System.out.println("Content of file " + path + ":");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

