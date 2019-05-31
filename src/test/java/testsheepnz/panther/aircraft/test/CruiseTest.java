package testsheepnz.panther.aircraft.test;

import org.junit.BeforeClass;
import org.junit.Test;
import testsheepnz.panther.page.ClimbPage;
import testsheepnz.panther.page.CruisePage;
import testsheepnz.panther.page.StatusPage;
import testsheepnz.panther.util.SetupAssistant;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class CruiseTest extends BaseTest {

    @BeforeClass
    public static void setAppropriateLogName() {
        String calledFrom = "CruiseTest";
        testLog.appendLogFileNameAccordingToTestsRun(calledFrom);
    }

    @Test
    public void standardEquipmentAtAltitude30000ftSpeed500ftsFor100nmUses238kg() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Set up standard equipment";
        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setupStandardAircraftFromEquipmentForm("2000");
        takeScreenshot(testDescription);
        assistant.selectLoadFromEquipmentForm();

        testDescription = "Apply climb of 30000";
        assistant.setHeightFromStatusForm("30000");
        takeScreenshot(testDescription);

        testDescription = "Apply cruise of 500kts for 100nm";
        assistant.selectCruiseFromStatusForm();
        assistant.applyCruiseFromCruiseForm("500","100");

        testDescription = "Fuel used is 238kg +/- 1kg";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        assertEquals(testDescription, statusPage.getFuelUsedLastLeg(), 238, 1);
        testPasses=Boolean.TRUE;
    }


}
