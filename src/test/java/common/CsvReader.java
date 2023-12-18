package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public List<String[]> readCsvFile(String csvFilePath) {
        String line;
        String csvSeparator = ",";
        List<String[]> csvData = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFilePath))) {
            // Skip the header line
            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                // Use the specified CSV separator
                String[] row = line.split(csvSeparator);

                // Check for null before adding to the list
                csvData.add(row);
            }
        } catch (IOException ioException) {
            System.err.println("Error reading CSV file: " + ioException.getMessage());
        }
        return csvData;
    }
}