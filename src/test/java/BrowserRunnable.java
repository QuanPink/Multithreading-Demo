import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class BrowserRunnable extends Thread {
    private final int width;
    private final int height;
    private final int position;
    private final int row;

    public BrowserRunnable(int width, int height, int position, int row) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.row = row;
    }

    @Override
    public void run() {
        while (true) {
            WebDriver driver = null;
            try {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();

                driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
                driver.manage().window().setPosition(new org.openqa.selenium.Point(position, row));

                driver.get("https://www.google.com/?hl=vi");
                driver.findElement(By.xpath("//textarea[@type = 'search']")).sendKeys("cháo");

                Actions action = new Actions(driver);

                action.sendKeys(Keys.ENTER).build().perform();
                driver.findElement(By.xpath("(//h3)[1]")).click();
            } finally {
                if (driver != null) {
                    driver.quit();
                }
            }
        }
    }
}
