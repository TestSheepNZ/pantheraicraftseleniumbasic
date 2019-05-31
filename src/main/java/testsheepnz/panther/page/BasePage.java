package testsheepnz.panther.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import testsheepnz.panther.util.SeleniumInstance;
import testsheepnz.panther.util.TestProperties;

public class BasePage {
    protected WebDriver driver;
    protected TestProperties properties;
    protected WebDriverWait wait;

    public BasePage(SeleniumInstance test) {
        this.driver = test.getDriver();
        this.properties = test.getTestProperties();
        this.wait = test.getWait();

        //Enable for POM model - https://github.com/SeleniumHQ/selenium/wiki/PageFactory
        PageFactory.initElements(driver, this);
    }

    public void waitForPage() {

    }
}
