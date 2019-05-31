package testsheepnz.panther.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testsheepnz.panther.util.SeleniumInstance;
import org.openqa.selenium.By;

public class ClimbPage extends BasePage {
    //Define the IDs ...
    private static final String CLIMB_FORM = "climbForm";
    private static final String CLIMB_ALTITUDE_FIELD = "climbAltitude";
    private static final String CLIMB_APPLY_BUTTON = "applyClimbButton";

    public ClimbPage(SeleniumInstance test) {
        super(test);
    }

    // Wait for page
    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CLIMB_FORM)));
    }

    public void waitForPageToVanish() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(CLIMB_FORM)));
    }

    //Altitude field
    @FindBy(id = CLIMB_ALTITUDE_FIELD)
    private WebElement altitudeField;

    public int getClimbAltitude() { return Integer.parseInt(altitudeField.getAttribute("value")); }

    public void setClimbAltitude(final String enterStr) {
        altitudeField.clear();
        altitudeField.sendKeys(enterStr);
    }


    //Select apply button
    @FindBy(id = CLIMB_APPLY_BUTTON)
    private WebElement applyClimbButton;

    public void clickApplyButton() {
        applyClimbButton.click();
    }

}
