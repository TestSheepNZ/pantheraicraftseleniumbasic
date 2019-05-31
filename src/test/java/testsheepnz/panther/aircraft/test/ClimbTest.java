package testsheepnz.panther.aircraft.test;

import org.junit.BeforeClass;
import org.junit.Test;
import testsheepnz.panther.page.ClimbPage;
import testsheepnz.panther.page.StatusPage;
import testsheepnz.panther.util.SetupAssistant;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class ClimbTest extends BaseTest {



    @BeforeClass
    public static void setAppropriateLogName() {
        String calledFrom = "ClimbTest";
        testLog.appendLogFileNameAccordingToTestsRun(calledFrom);
    }

    @Test
    public void settingAltitude20000BecomesNewAltitude() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Set up standard equipment";
        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setupStandardAircraftFromEquipmentForm("2000");
        takeScreenshot(testDescription);
        assistant.selectLoadFromEquipmentForm();

        testDescription = "Select climb";
        assistant.selectClimbFromStatusForm();
        takeScreenshot(testDescription);

        testDescription = "Enter an altitude of 2000ft";
        int newAltitude = 2000;
        ClimbPage climbPage = new ClimbPage(seleniumInstance);
        climbPage.waitForPage();
        climbPage.setClimbAltitude(Integer.toString(newAltitude));
        takeScreenshot(testDescription);

        testDescription = "Apply, new altitude is 20000ft";
        climbPage.clickApplyButton();
        climbPage.waitForPageToVanish();

        StatusPage statusPage = new StatusPage(seleniumInstance);
        assertEquals(testDescription, statusPage.getHeight(), newAltitude);
        testPasses=Boolean.TRUE;
    }

}
