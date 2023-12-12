import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BrowserManager {
    private static final int NUMBER_OF_TABS = 20;
    private static final int TAB_WIDTH = 200;
    private static final int TAB_HEIGHT = 600;
    private static final int POSITION_INCREMENT = 500;
    private static final int ROW_INCREMENT = 200;

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();

        try (ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_TABS)) {
            int position = 0;
            int row = 0;

            for (int i = 0; i < NUMBER_OF_TABS; i++) {
                BrowserRunnable browserRunnable = new BrowserRunnable(TAB_WIDTH, TAB_HEIGHT, position, row);
                position += POSITION_INCREMENT;

                if (position + TAB_WIDTH > screenWidth) {
                    position = 0;
                    switch (row) {
                        case 0, 200, 400, 600, 800, 1000:
                            row += ROW_INCREMENT;
                            break;
                    }
                }
                executor.execute(browserRunnable);
            }
        }
    }
}