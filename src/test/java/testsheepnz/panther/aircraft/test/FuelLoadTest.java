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

    @Test
    public void fuelLoadOf3001kgRejectedWithNoFuelTank() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Reject loading aircraft with 3001kg with no fuel tank";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.setInitialFuelField("3001");
        equipPage.clickLoadButton();
        takeScreenshot(testDescription);
        assertThat(testDescription, equipPage.getErrorMessage(), containsString(testProperties.ERROR_MESSAGE_NO_FUEL_TANKS));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void fuelLoadOf6001kgRejectedWithFuelTank() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Reject loading aircraft with 6001kg with fuel tank";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.selectFuelTank();
        equipPage.setInitialFuelField("6001");
        equipPage.clickLoadButton();
        takeScreenshot(testDescription);
        assertThat(testDescription, equipPage.getErrorMessage(), containsString(testProperties.ERROR_MESSAGE_WITH_FUEL_TANKS));
        testPasses=Boolean.TRUE;
    }

    // Fuel limit boundary
    @Test
    public void maxFuel3000kgWithoutFuelTanks() {
        seleniumInstance.goHome();

        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setUpAircraftFromEquipmentForm(   "3000",
                                            8,
                                         6,
                                           Boolean.FALSE,
                                           Boolean.FALSE,
                                           Boolean.FALSE );
        assistant.selectLoadFromEquipmentForm();

        String testDescription = "Setup aircraft with fuel tank accepts 3000kg of fuel";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        takeScreenshot(testDescription);
        assertEquals(testDescription, 3000, statusPage.getFuelRemaining());
        testPasses=Boolean.TRUE;
    }

    @Test
    public void maxFuel6000kgWithFuelTanks() {
        seleniumInstance.goHome();

        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setUpAircraftFromEquipmentForm(   "6000",
                                            8,
                                            6,
                                            Boolean.FALSE,
                                            Boolean.FALSE,
                                            Boolean.TRUE );
        assistant.selectLoadFromEquipmentForm();

        String testDescription = "Setup aircraft with fuel tank accepts 6000kg of fuel";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        takeScreenshot(testDescription);
        assertEquals(testDescription, 6000, statusPage.getFuelRemaining());
        testPasses=Boolean.TRUE;
    }

}
