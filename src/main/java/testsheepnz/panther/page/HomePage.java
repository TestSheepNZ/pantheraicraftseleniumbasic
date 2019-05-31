package testsheepnz.panther.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import testsheepnz.panther.util.SeleniumInstance;


public class HomePage extends BasePage {

    private static final String PANTHER_ODM_BUTTON = "gotoPantherODMButton";

    //ODM Button
    @FindBy(id = PANTHER_ODM_BUTTON )
    private WebElement odmButton;

    public HomePage(SeleniumInstance test) {
        super(test);
    }

    public void clickODMButton() {
        odmButton.click();
    }

    public Boolean loadButtonDisplayed() { return odmButton.isDisplayed(); }

    // Wait for page
    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PANTHER_ODM_BUTTON)));
    }
}
