package testsheepnz.panther.util;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;


//import java.time.Duration;


public class SeleniumInstance {

    private WebDriver driver;
    private WebDriverWait wait;
    private TestProperties testProperties;

    //private static final int TIMEOUT = 5;
    private static final int POLLING = 100;

    private SeleniumInstance() {
        testProperties = new TestProperties();
        this.launchDriver();
    }

    public SeleniumInstance(TestProperties properties) {
        testProperties = properties;
        this.launchDriver();
    }

    private void launchDriver() {
        String chromeDriverPath = testProperties.getDriverPath() + "chromedriver.exe";
        String firefoxDriverPath = testProperties.getDriverPath() + "geckodriver.exe";
        if(testProperties.getBrowserType().equals(BrowserType.FIREFOX)) {
            System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
            driver = new FirefoxDriver();
        } else if (testProperties.getBrowserType().equals(BrowserType.CHROME_HEADLESS)) {
            ChromeOptions chromeOptions = new ChromeOptions();
            //chromeOptions.setBinary("/Applications/Google Chrome Canary.app/Contents/MacOS/Google Chrome Canary");
            chromeOptions.addArguments("headless");
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            driver = new ChromeDriver(chromeOptions);
        } else {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            driver = new ChromeDriver();
        }

        wait = new WebDriverWait(driver, testProperties.getMaxWait(), POLLING);
        //wait = new WebDriverWait(driver, testProperties.getMaxWait());
    }

    public WebDriver getDriver() {
        return driver;
    }

    public TestProperties getTestProperties() {
        return testProperties;
    }

    public void get(String pageSource) {
        driver.get(pageSource);
    }

    public void goHome() {
        driver.get(testProperties.getWebsiteToTest());
        driver.get(testProperties.getWebsiteToTest() + "#calculator");
    }

    public void quit() {
        //driver.close();
        driver.quit();
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public String getPageContent() {
        return driver.getPageSource();
    }

    public void setBrowserSize() {
        Dimension dimension = new Dimension(testProperties.getBrowserWidth(), testProperties.getBrowserHeight());
        driver.manage().window().setSize(dimension);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public WebDriverWait getWait() {
        return wait;
    }

}
