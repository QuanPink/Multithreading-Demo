package setup;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BrowserManager {
    private static final int NUMBER_OF_TABS = 10;
    private static final int TAB_LIMIT = 5;
    private static final int TAB_WIDTH = 200;
    private static final int TAB_HEIGHT = 600;
    private static final int POSITION_INCREMENT = 500;
    private static final int ROW_INCREMENT = 200;

    public static void main(String[] args) {
        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();

        // Create a thread pool with the number of threads equal to the number of tabs to open
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_TABS);
        try {
            for (int i = 0; i < NUMBER_OF_TABS; i++) {

                // Calculate tab position
                int position = (i % (screenWidth / POSITION_INCREMENT)) * POSITION_INCREMENT;
                int row = (i / (screenWidth / POSITION_INCREMENT)) * ROW_INCREMENT;

                // Create a Runnable to open the tab
                BrowserRunnable browserRunnable = new BrowserRunnable(TAB_LIMIT, TAB_WIDTH, TAB_HEIGHT, position, row);
                // Send the Runnable to the thread pool for execution
                executorService.submit(browserRunnable);
            }
        } finally {
            // Ensure that the thread pool is turned off after all tasks have completed
            executorService.shutdown();
        }
    }
}