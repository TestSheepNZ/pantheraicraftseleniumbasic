package testsheepnz.panther.aircraft.test;

import org.junit.BeforeClass;
import org.junit.Test;
import testsheepnz.panther.page.EquipmentPage;
import org.junit.Assert;
import testsheepnz.panther.page.HomePage;
import testsheepnz.panther.page.StatusPage;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class EquipmentFormTest extends BaseTest {

    @BeforeClass
    public static void setAppropriateLogName() {
        String calledFrom = "EquipmentFormTest";
        testLog.appendLogFileNameAccordingToTestsRun(calledFrom);
    }

    @Test
    public void clickThroughFromHomepageWorks() {

        String testDescription = "Homepage title is TestSheepNZ's page";
        seleniumInstance.get(testProperties.getWebsiteHomepage());
        HomePage homePage = new HomePage(seleniumInstance);
        homePage.waitForPage();
        takeScreenshot(testDescription);
        assertThat(testDescription, seleniumInstance.getTitle(), containsString("TestSheepNZ Resource Page"));

        testDescription = "Clicking ODM button takes to Panther Fuel Calculator titled page";
        homePage.clickODMButton();
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertThat(testDescription, seleniumInstance.getTitle(), containsString("Panther Fuel Calculator"));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void pageOpensSuccessfully() {
        seleniumInstance.goHome();

        String testDescription = "Open page, it should contain Panther ODM";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertThat(testDescription, seleniumInstance.getPageContent(), containsString("Panther ODM"));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void complexTest() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Should reject adding 11 missiles";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        //equipPage.setInitialFuelField("2222");
        takeScreenshot(testDescription);
        assertFalse(testDescription, equipPage.attemptNumMissile(11));

        testDescription = "Should accepts adding 3 missiles";
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.attemptNumMissile(3));

        testDescription = "Should reject adding 11 bombs";
        takeScreenshot(testDescription);
        assertFalse(testDescription, equipPage.attemptNumDumbBomb(11));

        testDescription = "Should accepts adding 3 bombs";
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.attemptNumDumbBomb(3));

        testDescription = "Add a recon pod";
        equipPage.selectReconPod();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.isReconPodSelected());

        testDescription = "Bombs cleared";
        takeScreenshot(testDescription);
        assertEquals(testDescription, 0, equipPage.getNumDumbBomb());

        testDescription = "Add a Intelli bomb";
        equipPage.selectIntelliBomb();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.isIntelliBombSelected());

        testDescription = "Recon pod cleared";
        takeScreenshot(testDescription);
        assertFalse(testDescription, equipPage.isReconPodSelected());

        testDescription = "Load rejected with no fuel set";
        equipPage.clickLoadButton();
        takeScreenshot(testDescription);
        assertThat(testDescription, equipPage.getErrorMessage(), containsString(testProperties.ERROR_MESSAGE_NO_FUEL_TANKS));

        testDescription = "Load rejected with no fuel set, with different levels";
        equipPage.selectFuelTank();
        equipPage.clickLoadButton();
        takeScreenshot(testDescription);
        assertThat(testDescription, equipPage.getErrorMessage(), containsString(testProperties.ERROR_MESSAGE_WITH_FUEL_TANKS));

        testDescription = "Load rejected with too much fuel set";
        equipPage.setInitialFuelField("9999");
        equipPage.clickLoadButton();
        takeScreenshot(testDescription);
        assertThat(testDescription, equipPage.getErrorMessage(), containsString(testProperties.ERROR_MESSAGE_WITH_FUEL_TANKS));

        testDescription = "Add valid fuel load";
        equipPage.setInitialFuelField("4000");
        takeScreenshot(testDescription);

        testDescription = "Click load and wait for Status page";
        equipPage.clickLoadButton();

        //Call statusPage for the Selenium Instance and confirm loaded
        StatusPage statusPage = new StatusPage(seleniumInstance);
        takeScreenshot(testDescription);
        statusPage.waitForPage();

        //If it gets to the end, test passes
        testPasses=Boolean.TRUE;
    }





}
