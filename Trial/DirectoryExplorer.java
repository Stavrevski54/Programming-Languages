// Assignment 2: Directory Explorer
// Create a Java application called "DirectoryExplorer" that offers advanced directory and file exploration capabilities. The application should allow users to navigate 
// through the file system, perform searches based on different criteria, and display detailed information about files and directories.

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;

public class DirectoryExplorer {
    private static Path currentDirectory;

    public static void main(String[] args) {
        // Set the current directory to the application's startup directory
        currentDirectory = Paths.get("").toAbsolutePath();

        Scanner scanner = new Scanner(System.in);

        // Display initial prompt
        System.out.println("Welcome to Directory Explorer!");
        printPrompt();

        // Continuous interaction loop
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            // Split the input into command and arguments
            String[] tokens = input.split("\\s+");
            String command = tokens[0].toLowerCase();

            try {
                switch (command) {
                    case "navigate":
                        navigate(tokens);
                        break;
                    case "list":
                        listContents();
                        break;
                    case "search-files":
                        searchFiles(tokens);
                        break;
                    case "details":
                        fileDetails(tokens);
                        break;
                    case "list-size":
                        listBySize(tokens);
                        break;
                    case "compare":
                        fileComparison(tokens);
                        break;
                    case "tree-view":
                        directoryTreeView(currentDirectory.toFile(), 0);
                        break;
                    case "filter-extension":
                        filterByExtension(tokens);
                        break;
                    case "exit":
                        System.out.println("Exiting Directory Explorer. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid command. Type 'help' for a list of commands.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            printPrompt();
        }
    }

    // Helper method to print the command prompt
    private static void printPrompt() {
        System.out.print(currentDirectory + "> ");
    }

    // Method to navigate to a specified directory
    private static void navigate(String[] tokens) {
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Usage: navigate <path>");
        }

        String newPath = tokens[1];
        Path newDirectory = Paths.get(newPath);

        if (!Files.isDirectory(newDirectory)) {
            throw new IllegalArgumentException("Invalid directory path.");
        }

        currentDirectory = newDirectory;
    }

    // Method to list contents of the current directory
    private static void listContents() throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDirectory)) {
            for (Path file : stream) {
                System.out.println(file.getFileName());
            }
        }
    }

    // Method to search files based on a keyword
    private static void searchFiles(String[] tokens) throws IOException {
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Usage: search-files <keyword>");
        }

        String keyword = tokens[1].toLowerCase();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDirectory)) {
            for (Path file : stream) {
                if (file.getFileName().toString().toLowerCase().contains(keyword)) {
                    System.out.println(file.getFileName());
                }
            }
        }
    }

    // Method to display details of a file
    private static void fileDetails(String[] tokens) throws IOException {
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Usage: details <filename>");
        }

        String filename = tokens[1];
        Path filePath = currentDirectory.resolve(filename);

        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("File not found.");
        }

        BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
        System.out.println("Name: " + filePath.getFileName());
        System.out.println("Size: " + attrs.size() + " bytes");
        System.out.println("Last Modified: " + attrs.lastModifiedTime());
        System.out.println("Type: " + (Files.isDirectory(filePath) ? "Directory" : "File"));
    }

    // Method to list files within a size range
    private static void listBySize(String[] tokens) throws IOException {
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Usage: list-size <min-size> <max-size>");
        }

        long minSize = Long.parseLong(tokens[1]);
        long maxSize = Long.parseLong(tokens[2]);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDirectory)) {
            for (Path file : stream) {
                BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
                if (attrs.size() >= minSize && attrs.size() <= maxSize) {
                    System.out.println(file.getFileName());
                }
            }
        }
    }

    // Method to compare two files
    private static void fileComparison(String[] tokens) throws IOException {
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Usage: compare <file1> <file2>");
        }

        String file1Name = tokens[1];
        String file2Name = tokens[2];

        Path file1 = currentDirectory.resolve(file1Name);
        Path file2 = currentDirectory.resolve(file2Name);

        if (!Files.exists(file1) || !Files.exists(file2)) {
            throw new FileNotFoundException("One or both files not found.");
        }

        boolean areEqual = Files.mismatch(file1, file2) == -1;
        if (areEqual) {
            System.out.println("Files are identical.");
        } else {
            System.out.println("Files are different.");
        }
    }

    // Method to display directory tree view
    private static void directoryTreeView(File directory, int level) {
        if (directory.isDirectory()) {
            System.out.println(getIndentation(level) + directory.getName());
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    directoryTreeView(file, level + 1);
                }
            }
        }
    }

    // Helper method to get indentation for tree view
    private static String getIndentation(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

    // Method to filter files by extension
    private static void filterByExtension(String[] tokens) throws IOException {
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Usage: filter-extension <extension>");
        }

        String extension = tokens[1].toLowerCase();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDirectory)) {
            for (Path file : stream) {
                if (Files.isRegularFile(file) && file.toString().toLowerCase().endsWith(extension)) {
                    System.out.println(file.getFileName());
                }
            }
        }
    }
}
