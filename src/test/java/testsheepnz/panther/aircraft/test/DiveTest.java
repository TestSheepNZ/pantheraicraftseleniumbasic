package testsheepnz.panther.aircraft.test;

import org.junit.BeforeClass;
import org.junit.Test;
import testsheepnz.panther.page.ClimbPage;
import testsheepnz.panther.page.DivePage;
import testsheepnz.panther.page.StatusPage;
import testsheepnz.panther.util.SetupAssistant;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class DiveTest extends BaseTest {

    @BeforeClass
    public static void setAppropriateLogName() {
        String calledFrom = "DiveTest";
        testLog.appendLogFileNameAccordingToTestsRun(calledFrom);
    }

    @Test
    public void settingAltitude10000BecomesNewAltitude() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Set up standard equipment";
        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setupStandardAircraftFromEquipmentForm("2000");
        takeScreenshot(testDescription);
        assistant.selectLoadFromEquipmentForm();

        testDescription = "Set altitude to 30000ft";
        assistant.setHeightFromStatusForm("30000");
        takeScreenshot(testDescription);

        testDescription = "Select dive";
        assistant.selectDiveFromStatusForm();
        takeScreenshot(testDescription);

        testDescription = "Enter an altitude of 10000ft";
        int newAltitude = 10000;
        DivePage divePage = new DivePage(seleniumInstance);
        divePage.waitForPage();
        divePage.setDiveAltitude(Integer.toString(newAltitude));
        takeScreenshot(testDescription);

        testDescription = "Apply, new altitude is 10000ft";
        divePage.clickApplyButton();
        divePage.waitForPageToVanish();

        StatusPage statusPage = new StatusPage(seleniumInstance);
        assertEquals(testDescription, statusPage.getHeight(), newAltitude);
        testPasses=Boolean.TRUE;
    }
}
