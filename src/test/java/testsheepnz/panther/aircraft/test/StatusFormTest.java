package testsheepnz.panther.aircraft.test;

import org.junit.BeforeClass;
import org.junit.Test;
import testsheepnz.panther.page.ClimbPage;
import testsheepnz.panther.page.StatusPage;
import testsheepnz.panther.util.SetupAssistant;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;


public class StatusFormTest extends BaseTest {

    @BeforeClass
    public static void setAppropriateLogName() {
        String calledFrom = "StatusTest";
        testLog.appendLogFileNameAccordingToTestsRun(calledFrom);
    }

    @Test
    public void aircraftWith6000kgFuel8Missiles6DumbBombsExternalFuelTanksWeight() {
        seleniumInstance.goHome();

        //Use assistant to setup aircraft
        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setUpAircraftFromEquipmentForm(   "6000",
                                                    8,
                                                6,
                                                   Boolean.FALSE,
                                                   Boolean.FALSE,
                                                   Boolean.TRUE );
        assistant.selectLoadFromEquipmentForm();

        String testDescription = "All up weight should be 17000kg";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        takeScreenshot(testDescription);
        assertEquals(testDescription, 17000, statusPage.getWeight());
        testPasses=Boolean.TRUE;
    }

    @Test
    public void setupAircraftStartsWithLeg0() {
        seleniumInstance.goHome();

        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setupStandardAircraftFromEquipmentForm("2000");
        assistant.selectLoadFromEquipmentForm();

        String testDescription = "Setup aircraft starts with leg 0";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        takeScreenshot(testDescription);
        assertEquals(testDescription, 0, statusPage.getLegNumber());
        testPasses=Boolean.TRUE;
    }

}
