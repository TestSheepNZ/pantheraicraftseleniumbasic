package testsheepnz.panther.aircraft.test;

import org.junit.BeforeClass;
import org.junit.Test;
import testsheepnz.panther.page.EquipmentPage;
import testsheepnz.panther.page.StatusPage;
import testsheepnz.panther.util.SetupAssistant;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class FuelLoadTest extends BaseTest {

    @BeforeClass
    public static void setAppropriateLogName() {
        String calledFrom = "FuelLoadTest";
        testLog.appendLogFileNameAccordingToTestsRun(calledFrom);
    }

    // Fuel limit UNDER
    @Test
    public void fuelLoadOfEmptyRejected() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Reject loading aircraft with no fuel";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.clickLoadButton();
        takeScreenshot(testDescription);
        assertThat(testDescription, equipPage.getErrorMessage(), containsString(testProperties.ERROR_MESSAGE_NO_FUEL_TANKS));
        testPasses=Boolean.TRUE;
    }

    // Fuel limit OVER
    @Test
    public void fuelLoadOf0kgRejected() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Reject loading aircraft with 0kg fuel";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.setInitialFuelField("0");
        equipPage.clickLoadButton();
        takeScreenshot(testDescription);
        assertThat(testDescription, equipPage.getErrorMessage(), containsString(testProperties.ERROR_MESSAGE_NO_FUEL_TANKS));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void fuelLoadOfTextRejected() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Reject loading aircraft with text string characters in fuel";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.setInitialFuelField("hello");
        equipPage.clickLoadButton();
        takeScreenshot(testDescription);
        assertThat(testDescription, equipPage.getErrorMessage(), containsString(testProperties.ERROR_MESSAGE_NO_FUEL_TANKS));
        testPasses=Boolean.TRUE;
    }



}
