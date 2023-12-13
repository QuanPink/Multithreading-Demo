import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class BrowserRunnable implements Runnable {
    private final int tabLimit;
    private final int width;
    private final int height;
    private final int position;
    private final int row;
    private int openedTabs = 0;

    public BrowserRunnable(int tabLimit, int width, int height, int position, int row) {
        this.tabLimit = tabLimit;
        this.width = width;
        this.height = height;
        this.position = position;
        this.row = row;
    }

    @Override
    public void run() {
        // Open tabs until limit is reached
        while (openedTabs < tabLimit) {
            WebDriver driver = null;
            try {
                // Install and initialize WebDriver
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();

                // Set the size and position of the window
                driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
                driver.manage().window().setPosition(new org.openqa.selenium.Point(position, row));

                driver.get("https://www.google.com/?hl=vi");
                driver.findElement(By.xpath("//textarea[@type = 'search']")).sendKeys("cháo");

                Actions action = new Actions(driver);

                action.sendKeys(Keys.ENTER).build().perform();
                driver.findElement(By.xpath("(//h3)[1]")).click();

                // Increase the number of opened tabs
                openedTabs++;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Make sure WebDriver is turned off upon completion
                if (driver != null) {
                    driver.quit();
                }
            }
        }
    }
}