package setup;

import common.CsvReader;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BrowserManager {
    private static final int NUMBER_OF_TABS = 5;
    protected static final int BROWSER_LIMIT = 11;
    private static final int TAB_WIDTH = 200;
    private static final int TAB_HEIGHT = 600;
    private static final int POSITION_INCREMENT = 500;
    private static final int ROW_INCREMENT = 200;
    private static int accountIndex = 0;

    public static void main(String[] args) {
        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        //Read csv file
        CsvReader csvReader = new CsvReader();
        List<String[]> accounts = csvReader.readCsvFile("src/test/java/resources/Book1.csv");

        // Create a thread pool with the number of threads equal to the number of tabs to open
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_TABS);
        try {
            for (int i = 0; i < BROWSER_LIMIT; i++) {
                // If index is out of bounds, reset it to 0
                if (accountIndex >= accounts.size()) {
                    accountIndex = 0;
                }

                // Get current data from accounts list
                String[] currentData = accounts.get(accountIndex);
                String username = currentData[0];
                String password = currentData[1];

                // Calculate tab position
                int position = (i % (screenWidth / POSITION_INCREMENT)) * POSITION_INCREMENT;
                int row = (i / (screenWidth / POSITION_INCREMENT)) * ROW_INCREMENT;

                // Create a Runnable to open the tab
                BrowserRunnable browserRunnable = new BrowserRunnable(TAB_WIDTH, TAB_HEIGHT, position, row, username, password);
                // Send the Runnable to the thread pool for execution
                executorService.submit(browserRunnable);

                //Increase the accountIndex
                accountIndex++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure that the thread pool is turned off after all tasks have completed
            executorService.shutdown();
        }
    }
}