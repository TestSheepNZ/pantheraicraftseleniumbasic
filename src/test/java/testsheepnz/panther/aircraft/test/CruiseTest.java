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

    @Test
    public void standardEquipmentAtAltitude1000ftSpeed500ftsFor100nmUses478kg() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Set up standard equipment";
        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setupStandardAircraftFromEquipmentForm("2000");
        takeScreenshot(testDescription);
        assistant.selectLoadFromEquipmentForm();

        testDescription = "Apply climb of 1000";
        assistant.setHeightFromStatusForm("1000");
        takeScreenshot(testDescription);

        testDescription = "Apply cruise of 500kts for 100nm";
        assistant.selectCruiseFromStatusForm();
        assistant.applyCruiseFromCruiseForm("500","100");

        testDescription = "Fuel used is 478kg +/- 1kg";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        assertEquals(testDescription, statusPage.getFuelUsedLastLeg(), 478, 1);

        testPasses=Boolean.TRUE;
    }

    @Test
    public void applyingCruiseIncrementsLeg() {
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

        testDescription = "Current leg is 1";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        assertEquals(testDescription, statusPage.getLegNumber(), 1);

        testDescription = "Apply cruise of 500kts for 100nm";
        assistant.selectCruiseFromStatusForm();
        assistant.applyCruiseFromCruiseForm("500","100");

        testDescription = "Current leg is 2";
        assertEquals(testDescription, statusPage.getLegNumber(), 2);
        testPasses=Boolean.TRUE;
    }

    @Test
    public void errorIfCruiseSetWithInsufffientFuel() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Set up standard equipment";
        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setupStandardAircraftFromEquipmentForm("100");
        takeScreenshot(testDescription);
        assistant.selectLoadFromEquipmentForm();

        testDescription = "Apply climb of 1000";
        assistant.setHeightFromStatusForm("1000");
        takeScreenshot(testDescription);

        testDescription = "Apply cruise of 500kts for 100nm";
        assistant.selectCruiseFromStatusForm();
        CruisePage cruisePage = new CruisePage(seleniumInstance);
        cruisePage.waitForPage();
        cruisePage.setSpeed("500");
        cruisePage.setDistance("100");
        cruisePage.applyCruiseButton();
        takeScreenshot(testDescription);

        testDescription = "Error message of insufficient fuel displayed";
        assertThat(testDescription, assistant.getErrorMessage(), containsString(testProperties.ERROR_MESSAGE_INSUFFICIENT_FUEL));
        testPasses=Boolean.TRUE;
    }


}
