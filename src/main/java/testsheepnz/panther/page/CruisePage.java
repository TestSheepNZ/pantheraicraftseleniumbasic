package testsheepnz.panther.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testsheepnz.panther.util.SeleniumInstance;
import org.openqa.selenium.By;

public class CruisePage extends BasePage {

    //Define the IDs ...
    private static final String CRUISE_FORM = "cruiseForm";
    private static final String CRUISE_SPEED_FIELD = "aircraftSpeed";
    private static final String CRUISE_DISTANCE_FIELD = "aircraftDistance";
    private static final String CRUISE_APPLY_BUTTON = "applyCruiseButton";

    public CruisePage(SeleniumInstance test) {
        super(test);
    }

    // Wait for page
    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CRUISE_FORM)));
    }

    public void waitForPageToVanish() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(CRUISE_FORM)));
    }


    //Leg field
    @FindBy(id = CRUISE_SPEED_FIELD)
    private WebElement speedField;

    public int getSpeed() { return Integer.parseInt(speedField.getAttribute("value")); }

    public void setSpeed(final String enterStr) {
        speedField.clear();
        speedField.sendKeys(enterStr);
    }

    //Leg field
    @FindBy(id = CRUISE_DISTANCE_FIELD)
    private WebElement distanceField;

    public int getDistance() { return Integer.parseInt(distanceField.getAttribute("value")); }

    public void setDistance(final String enterStr) {
        distanceField.clear();
        distanceField.sendKeys(enterStr);
    }

    //Select apply button
    @FindBy(id = CRUISE_APPLY_BUTTON)
    private WebElement applyCruiseButton;

    public void applyCruiseButton() {
        applyCruiseButton.click();
    }

}
