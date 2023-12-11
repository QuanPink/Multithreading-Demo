import io.github.bonigarcia.wdm.WebDriverManager;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int NUMBER_OF_TABS = 10;
    private static final int TAB_WIDTH = 200;
    private static final int TAB_HEIGHT = 600;
    private static final int POSITION_INCREMENT = 500;
    private static final int ROW_INCREMENT = 200;

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();

        WebDriverManager.chromedriver().setup();

        try (ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_TABS)) {
            int position = 0;
            int row = 0;

            for (int i = 0; i < NUMBER_OF_TABS; i++) {
                BrowserRunnable browserRunnable = new BrowserRunnable(TAB_WIDTH, TAB_HEIGHT, position, row);
                position += POSITION_INCREMENT;

                if (position + TAB_WIDTH > screenWidth) {
                    position = 0;
                    row += ROW_INCREMENT;
                }
                executor.execute(browserRunnable);
            }
        }
    }
}