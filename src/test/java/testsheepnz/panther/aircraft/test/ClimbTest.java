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

    @Test
    public void climbOf30000ftUses300kgFuel() {
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

        testDescription = "Fuel used is 300kg";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        assertEquals(testDescription, statusPage.getFuelUsedLastLeg(), 300);
        testPasses=Boolean.TRUE;
    }

    @Test
    public void climbFrom10000To30000ftUses200kgFuel() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Set up standard equipment";
        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setupStandardAircraftFromEquipmentForm("2000");
        takeScreenshot(testDescription);
        assistant.selectLoadFromEquipmentForm();

        testDescription = "Apply climb of 10000";
        assistant.setHeightFromStatusForm("10000");
        takeScreenshot(testDescription);

        testDescription = "Apply climb of 30000";
        assistant.setHeightFromStatusForm("30000");
        takeScreenshot(testDescription);

        testDescription = "Fuel used is 200kg";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        assertEquals(testDescription, statusPage.getFuelUsedLastLeg(), 200);
        testPasses=Boolean.TRUE;
    }

    @Test
    public void applyingClimbIncrementsLeg() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Set up standard equipment";
        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setupStandardAircraftFromEquipmentForm("2000");
        takeScreenshot(testDescription);
        assistant.selectLoadFromEquipmentForm();

        testDescription = "Current leg is 0";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        assertEquals(testDescription, statusPage.getLegNumber(), 0);

        testDescription = "Apply climb of 10000";
        assistant.setHeightFromStatusForm("10000");
        takeScreenshot(testDescription);

        testDescription = "Current leg is 1";
        assertEquals(testDescription, statusPage.getLegNumber(), 1);
        testPasses=Boolean.TRUE;
    }

    @Test
    public void errorIfClimbSetWithInsufffientFuel() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Set up standard equipment";
        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setupStandardAircraftFromEquipmentForm("10");
        takeScreenshot(testDescription);
        assistant.selectLoadFromEquipmentForm();

        testDescription = "Set climb";
        assistant.selectClimbFromStatusForm();
        takeScreenshot(testDescription);

        testDescription = "Set climb of 30000, there should be insuffient fuel";
        ClimbPage climbPage = new ClimbPage(seleniumInstance);
        climbPage.waitForPage();
        climbPage.setClimbAltitude("30000");
        climbPage.clickApplyButton();
        assertThat(testDescription, assistant.getErrorMessage(), containsString(testProperties.ERROR_MESSAGE_INSUFFICIENT_FUEL));
        testPasses=Boolean.TRUE;
    }

}
