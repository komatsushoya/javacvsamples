import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;

class Sample1 extends Thread {

    boolean stop;
    int count;
    private WebDriver driver;

    Sample1(WebDriver driver) {
        this.driver = driver;
        this.stop = false;
        count = 0;
    }

    @Override
    public void run() {
        while (stop == false) {
            try {
                File sfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                InputStream is = new FileInputStream(sfile);
                OutputStream os = new FileOutputStream(new File("photo/jpg3/screenshot_" + count + ".png"));
                byte[] bytes = new byte[1024];
                int read = 0;
                while ((read = is.read(bytes)) != -1) {
                    os.write(bytes, 0, read);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
}

class Sample2 {
    void hogehoge(WebDriver driver, Sample1 smp1) {
        //And now use this to visit Google
        new Thread(smp1).start();
        long startTime = System.currentTimeMillis();
        driver.get("http://www.google.com");

        //Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        //Enter something to search for
        element.sendKeys("Cheese!");

        //Now submit the form. WebDriver will find the form for us from the element
        element.submit();
        //Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        //Google's search is rendered dynamically with JavaScript.
        //Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        //ここで停止
        smp1.stop = true;
        long endTime = System.currentTimeMillis();
        System.out.println("処理時間：" + (endTime - startTime) + " ms");
        //Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());

        //Close the browser
        driver.quit();
    }
}

public class Thread1 {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        Sample1 smp1 = new Sample1(driver);
        Sample2 smp2 = new Sample2();
        smp2.hogehoge(driver, smp1);
    }
}