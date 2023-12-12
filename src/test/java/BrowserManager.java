import java.awt.*;

public class BrowserManager {
    private static final int NUMBER_OF_TABS = 10;
    private static final int TAB_WIDTH = 200;
    private static final int TAB_HEIGHT = 600;
    private static final int POSITION_INCREMENT = 500;
    private static final int ROW_INCREMENT = 200;

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();

        for (int i = 0; i < NUMBER_OF_TABS; i++) {

            int position = (i % (screenWidth / POSITION_INCREMENT)) * POSITION_INCREMENT;
            int row = (i / (screenWidth / POSITION_INCREMENT)) * ROW_INCREMENT;

            BrowserRunnable browserRunnable = new BrowserRunnable(TAB_WIDTH, TAB_HEIGHT, position, row);
            browserRunnable.start();
        }
    }
}
