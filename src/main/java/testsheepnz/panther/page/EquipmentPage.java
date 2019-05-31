package testsheepnz.panther.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import testsheepnz.panther.util.SeleniumInstance;
import org.openqa.selenium.By;

public class EquipmentPage extends BasePage {

    //Define the IDs ...
    private static final String EQUIPMENT_FORM = "equipmentForm";
    private static final String EQUIPMENT_ERROR_MSG = "initErrorMsg";
    private static final String INITIAL_FUEL_FIELD = "initialFuel";
    private static final String NUM_MISSILES_DROPDOWN = "numMissiles";
    private static final String NUM_DUMB_BOMB_DROPDOWN = "numDumbBomb";
    private static final String RECON_POD_CHECKBOX = "reconPod";
    private static final String INTELLI_BOMB_CHECKBOX = "intelliBomb";
    private static final String FUEL_TANK_CHECKBOX = "fuelTank";

    private static final String LOAD_BUTTON = "setEquipmentButton";


    public EquipmentPage(SeleniumInstance test) {
        super(test);
    }

    // Wait for page
    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(EQUIPMENT_FORM)));
    }

    //Error field
    @FindBy(id = EQUIPMENT_ERROR_MSG)
    private WebElement errorMessage;

    public String getErrorMessage() { return errorMessage.getText();}

    public Boolean errorMessageIsBlank() { return this.getErrorMessage().isEmpty(); }

    //Fuel field
    @FindBy(id = INITIAL_FUEL_FIELD)
    private WebElement fuelField;

    public void setInitialFuelField(final String enterStr) {
        fuelField.clear();
        fuelField.sendKeys(enterStr);
    }

    public String getInitialFuelField() {
        return fuelField.getAttribute("value");
    }

    //Num missiles
    @FindBy(id = NUM_MISSILES_DROPDOWN)
    private WebElement numMissilesDropdown;

    public void setNumMissiles(final int numMissiles) {
        Select dropdown = new Select(numMissilesDropdown);
        dropdown.selectByIndex(numMissiles);
    }

    public Boolean attemptNumMissile(final int numMissiles) {
        Boolean retValue = Boolean.TRUE;

        try {
            this.setNumMissiles(numMissiles);
            return Boolean.TRUE;
        } catch (Exception e) {
            //log.error("Num missiles element " + numMissiles + " not found");
            return Boolean.FALSE;
        }
    }

    public int getNumMissile() {
        return Integer.parseInt(numMissilesDropdown.getAttribute("value"));
    }

    //Dumb bombs
    @FindBy(id = NUM_DUMB_BOMB_DROPDOWN)
    private WebElement numDumbBombDropdown;

    public void setNumDumbBomb(final int numDumbBomb) {
        Select dropdown = new Select(numDumbBombDropdown);
        dropdown.selectByIndex(numDumbBomb);
    }

    public Boolean attemptNumDumbBomb(final int numDumbBomb) {
        Boolean retValue = Boolean.TRUE;

        try {
            this.setNumDumbBomb(numDumbBomb);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public int getNumDumbBomb() {
        return Integer.parseInt(numDumbBombDropdown.getAttribute("value"));
    }

    //Recon pod
    @FindBy(id = RECON_POD_CHECKBOX)
    private WebElement reconPodCheckbox;

    public void selectReconPod() {
        reconPodCheckbox.click();
    }

    public Boolean isReconPodSelected () {
        return reconPodCheckbox.isSelected();
    }

    //Intelli bomb
    @FindBy(id = INTELLI_BOMB_CHECKBOX)
    private WebElement intelliBombCheckbox;

    public void selectIntelliBomb() {
        intelliBombCheckbox.click();
    }

    public Boolean isIntelliBombSelected () {
        return intelliBombCheckbox.isSelected();
    }

    //Fuel Tank
    @FindBy(id = FUEL_TANK_CHECKBOX)
    private WebElement fuelTankCheckbox;

    public void selectFuelTank() {
        fuelTankCheckbox.click();
    }

    public Boolean isFuelTankSelected () {
        return fuelTankCheckbox.isSelected();
    }

    //Set equipment button
    @FindBy(id = LOAD_BUTTON)
    private WebElement loadButton;

    public void clickLoadButton() {
        loadButton.click();
    }

    //Restart button
    private static final String RESTART_BUTTON = "restartButton";
    @FindBy(id = RESTART_BUTTON)
    private WebElement restartButton;

    public void clickRestartButton() {
        restartButton.click();
    }

}
