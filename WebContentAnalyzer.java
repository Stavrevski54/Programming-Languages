import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WebContentAnalyzer {

    public static void main(String[] args) {
        String url = "https://uacs.edu.mk/home/home/";
        String fileName = "downloaded_content.txt";
        String reportFileName = "count_report.txt";

        try {
            // Step 1: Connect to the URL, download content, and save it as a text file
            downloadContent(url, fileName);

            // Step 2: Analyze the document and save results in count_report.txt
            analyzeDocument(fileName, reportFileName);

            System.out.println("Process completed successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static void downloadContent(String url, String fileName) throws IOException {
        URL urlObject = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } finally {
            connection.disconnect();
        }
    }

    private static void analyzeDocument(String fileName, String reportFileName) throws IOException {
        int lineCount = 0;
        int maxCharacters = 0;
        int lineWithMaxCharacters = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;

                // Check if the current line has more characters than the current max
                if (line.length() > maxCharacters) {
                    maxCharacters = line.length();
                    lineWithMaxCharacters = lineCount;
                }
            }
        }
// Write the results to count_report.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFileName))) {
            writer.write("Number of lines in the document: " + lineCount);
            writer.newLine();
            writer.write("Line number with max characters: " + lineWithMaxCharacters);
            writer.newLine();
            writer.write("Number of characters in the line number with max characters: " + maxCharacters);
        }
    }
}
