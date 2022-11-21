package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author ${USER}
 * @CreatedDate ${DATE} ${TIME}
 */
public class Main {

    /**
     * price
     * document.getElementsByClassName('_2Shl1j')[0].innerHTML
     *
     * title
     * document.getElementsByClassName('_3frqjk')[0].innerHTML
     */

    static Queue<String> queue = new LinkedList<>();
    static Set<String> visited = new HashSet<>();

    public static void main(String[] args) throws IOException, InterruptedException {

        String chromeDriverPath = "C:/Users/PC/Downloads/chromedriver_win32/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeOptions options = new ChromeOptions();

        // options.setHeadless(true);

        options.addArguments("--window-size=1920,5080","--ignore-certificate-errors", "--silent"); // "--headless",

        WebDriver driver = new ChromeDriver(options);


        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        queue.add("https://shopee.vn/K%C3%ADnh-c%C6%B0%E1%BB%9Dng-l%E1%BB%B1c-ch%E1%BB%91ng-nh%C3%ACn-tr%E1%BB%99m-iphone-full-m%C3%A0n-%C4%91%E1%BB%A7-size-6-6plus-6splus-7-7plus-8-8plus-...-12-12ProMax-i.238930514.7581372990?sp_atk=42a978f9-9ea0-48ef-9161-4bc10693fced&xptdk=42a978f9-9ea0-48ef-9161-4bc10693fced");

        while (!queue.isEmpty()){
            try{
                String poll = queue.poll();
                visited.add(poll);
                driver.get(poll);


            }catch (Exception e){

            }finally {

                JavascriptExecutor js = (JavascriptExecutor) driver;
//Scroll down till the bottom of the page
                js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

                Thread.sleep(2000);
                List<WebElement> elements = driver.findElements(By.cssSelector("a[data-sqe='link']"));
                for (WebElement element : elements) {
                    String href = element.getAttribute("href");
                    String s = href.substring(0, href.indexOf('?'));

                    if(!visited.contains(s)){
                        queue.add(s);
                    }
                }

                // driver.findElements(By.cssSelector())

            }
        }



        // Take a screenshot of the current page
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileWriter f = new FileWriter("D:/test.html");

        f.write(driver.getPageSource());


        driver.quit();

    }
}