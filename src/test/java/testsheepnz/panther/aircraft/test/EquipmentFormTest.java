package testsheepnz.panther.aircraft.test;

import org.junit.BeforeClass;
import org.junit.Test;
import testsheepnz.panther.page.EquipmentPage;
import org.junit.Assert;
import testsheepnz.panther.page.HomePage;

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

        testDescription = "Should reject adding 10 missiles";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.setInitialFuelField("2222");
        equipPage.setNumMissiles(3);
        takeScreenshot(testDescription);
        assertFalse(testDescription, equipPage.attemptNumMissile(10));

        //If it gets to the end, test passes
        testPasses=Boolean.TRUE;
    }

    //Missile tests
    @Test
    public void canAdd0Missiles() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Should accept 0 missiles selection";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.attemptNumMissile(0));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void canAdd1Missiles() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Should accept 1 missiles selection";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.attemptNumMissile(1));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void canAdd8Missiles() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Should accept 8 missiles selection";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.attemptNumMissile(8));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void cannotAdd9Missiles() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Should reject 9 missiles selection";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertFalse(testDescription, equipPage.attemptNumMissile(9));
        testPasses=Boolean.TRUE;
    }

    //Dumb bomb tests
    @Test
    public void canAdd0DumbBomb() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Should accept 0 dumb bomb selection";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.attemptNumDumbBomb(0));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void canAdd1DumbBomb() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Should accept 1 dumb bomb selection";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.attemptNumDumbBomb(1));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void canAdd6DumbBomb() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Should accept 6 dumb bomb selection";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.attemptNumDumbBomb(6));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void cannotAdd7DumbBomb() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Should not accept 7 dumb bomb selection";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertFalse(testDescription, equipPage.attemptNumDumbBomb(7));
        testPasses=Boolean.TRUE;
    }

    //Recon pod
    @Test
    public void addAReconPod() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Add a recon pod";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.selectReconPod();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.isReconPodSelected());
        testPasses=Boolean.TRUE;
    }

    @Test
    public void toggleReconPod() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Add a recon pod";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.selectReconPod();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.isReconPodSelected());

        testDescription = "Select recon pod again to deselect";
        equipPage.selectReconPod();
        takeScreenshot(testDescription);
        assertFalse(testDescription, equipPage.isReconPodSelected());
        testPasses=Boolean.TRUE;
    }

    //Intelli bomb
    @Test
    public void addIntelliBomb() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Add intelli bomb";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.selectIntelliBomb();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.isIntelliBombSelected());
        testPasses=Boolean.TRUE;
    }

    @Test
    public void toggleIntelliBomb() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Add intelli bomb";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.selectIntelliBomb();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.isIntelliBombSelected());

        testDescription = "Select intelli bomb again to deselect";
        equipPage.selectIntelliBomb();
        takeScreenshot(testDescription);
        assertFalse(testDescription, equipPage.isIntelliBombSelected());
        testPasses=Boolean.TRUE;
    }

    //Fuel tank
    @Test
    public void addFuelTank() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Add fuel tank";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.selectFuelTank();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.isFuelTankSelected());
        testPasses=Boolean.TRUE;
    }

    @Test
    public void toggleFuelTank() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Add fuel tank";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.selectFuelTank();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.isFuelTankSelected());

        testDescription = "Select fuel tank again to deselect";
        equipPage.selectFuelTank();
        takeScreenshot(testDescription);
        assertFalse(testDescription, equipPage.isFuelTankSelected());
        testPasses=Boolean.TRUE;
    }

    //Mix of items tests

    @Test
    public void selectingIntelliBombClearsDumbBombs() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Select 6 dumb bomb";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.setNumDumbBomb(6);
        takeScreenshot(testDescription);
        assertEquals(testDescription, 6, equipPage.getNumDumbBomb());

        testDescription = "Selecting intelli bomb clears dumb bombs";
        equipPage.selectIntelliBomb();
        takeScreenshot(testDescription);
        assertTrue("Intelli bomb selected", equipPage.isIntelliBombSelected());
        assertEquals(testDescription, 0, equipPage.getNumDumbBomb());
        testPasses=Boolean.TRUE;
    }

    @Test
    public void selectingReconPodClearsDumbBombs() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Select 6 dumb bomb";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.setNumDumbBomb(6);
        takeScreenshot(testDescription);
        assertEquals(testDescription, 6, equipPage.getNumDumbBomb());

        testDescription = "Selecting recon pod clears dumb bombs";
        equipPage.selectReconPod();
        takeScreenshot(testDescription);
        assertTrue("Recon pod selected", equipPage.isReconPodSelected());
        assertEquals(testDescription, 0, equipPage.getNumDumbBomb());
        testPasses=Boolean.TRUE;
    }

    @Test
    public void selectingReconPodClearsIntelliBomb() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Add intelli bomb";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.selectIntelliBomb();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.isIntelliBombSelected());

        testDescription = "Selecting recon pod clears intelli bomb";
        equipPage.selectReconPod();
        takeScreenshot(testDescription);
        assertTrue("Recon pod selected", equipPage.isReconPodSelected());
        assertFalse(testDescription, equipPage.isIntelliBombSelected());
        testPasses=Boolean.TRUE;
    }

    @Test
    public void selectingIntelliBombClearsReconPod() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Add a recon pod";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.selectReconPod();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.isReconPodSelected());

        testDescription = "Selecting intelli bomb clears recon pod";
        equipPage.selectIntelliBomb();
        takeScreenshot(testDescription);
        assertTrue("Intelli bomb selected", equipPage.isIntelliBombSelected());
        assertFalse(testDescription, equipPage.isReconPodSelected());
        testPasses=Boolean.TRUE;
    }

    @Test
    public void selectingDumbBombClearsReconPod() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Add a recon pod";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.selectReconPod();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.isReconPodSelected());

        testDescription = "Selecting dumb bomb clears recon pod";
        equipPage.setNumDumbBomb(6);
        takeScreenshot(testDescription);
        assertEquals("6 dumb bomb selected", 6, equipPage.getNumDumbBomb());
        assertFalse(testDescription, equipPage.isReconPodSelected());
        testPasses=Boolean.TRUE;
    }

    @Test
    public void selectingDumbBombClearsIntelliBomb() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Add intelli bomb";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.selectIntelliBomb();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.isIntelliBombSelected());

        testDescription = "Selecting dumb bomb clears intelli bomb";
        equipPage.setNumDumbBomb(6);
        takeScreenshot(testDescription);
        assertEquals("6 dumb bomb selected", 6, equipPage.getNumDumbBomb());
        assertFalse(testDescription, equipPage.isIntelliBombSelected());
        testPasses=Boolean.TRUE;
    }
}
