package testsheepnz.panther.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import testsheepnz.panther.util.SeleniumInstance;
import org.openqa.selenium.By;

public class StatusPage extends BasePage {

    //Define the IDs ...
    private static final String STATUS_FORM = "statusForm";
    private static final String STATUS_ERROR_MSG = "statusErrorMsg";
    private static final String LEG_FIELD = "aircraftLeg";
    private static final String HEIGHT_FIELD = "aircraftHeight";
    private static final String WEIGHT_FIELD = "aircraftWeight";
    private static final String FUEL_REMAINING_FIELD = "aircraftFuelRemaining";
    private static final String FUEL_LAST_LEG_FIELD = "aircraftFuelLastLeg";
    private static final String MOVE_TYPE_DROPDOWN = "moveType";

    public StatusPage(SeleniumInstance test) {
        super(test);
    }

    // Wait for page
    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(STATUS_FORM)));
    }

    //Error msg
    @FindBy(id = STATUS_ERROR_MSG)
    private WebElement errorMessage;

    public String getErrorMessage() { return errorMessage.getText();}

    public Boolean errorMessageIsBlank() { return this.getErrorMessage().isEmpty(); }

    //Leg field
    @FindBy(id = LEG_FIELD)
    private WebElement legField;

    public int getLegNumber() { return Integer.parseInt(legField.getAttribute("value")); }

    //Height field
    @FindBy(id = HEIGHT_FIELD)
    private WebElement heightField;

    public int getHeight() { return Integer.parseInt(heightField.getAttribute("value")); }

    //Weight field
    @FindBy(id = WEIGHT_FIELD)
    private WebElement weightField;

    public int getWeight() { return Integer.parseInt(weightField.getAttribute("value")); }

    //Fuel remaining field
    @FindBy(id = FUEL_REMAINING_FIELD)
    private WebElement fuelField;

    public int getFuelRemaining() { return Math.round(Float.parseFloat(fuelField.getAttribute("value"))); }

    //Fuel remaining field
    @FindBy(id = FUEL_LAST_LEG_FIELD)
    private WebElement fuelUsedLastLegField;

    public int getFuelUsedLastLeg() { return Math.round(Float.parseFloat(fuelUsedLastLegField.getAttribute("value"))); }

    //Move type dropdown
    @FindBy(id = MOVE_TYPE_DROPDOWN)
    private WebElement moveType;

    public String getMoveType() { return moveType.getAttribute("value"); }

    public void setMoveByNum(final int moveOptionNum) {
        Select dropdown = new Select(moveType);
        dropdown.selectByIndex(moveOptionNum);
    }

    public void setMoveByText(final String moveOptionStr) {
        Select dropdown = new Select(moveType);
        dropdown.selectByVisibleText(moveOptionStr);
    }

    public void setCruise() {
        this.setMoveByText("Cruise");
    }

    public void setDive() {
        this.setMoveByText("Dive");
    }

    public void setClimb() {
        this.setMoveByText("Climb");
    }
    //Restart button
    private static final String RESTART_BUTTON = "restartButton";
    @FindBy(id = RESTART_BUTTON)
    private WebElement restartButton;

    public void clickRestartButton() {
        restartButton.click();
    }

}
