package testsheepnz.panther.util;

import testsheepnz.panther.page.*;


/*
 * This class helps with tests by doing a lot of setup for certain pages
 * This class assumes you to be on the page you need to be
 * it will also not perform any assertions - this it to set up an instance for testing
 * NOT for testing it
 */
public class SetupAssistant {

    private SeleniumInstance seleniumInstance;
    private TestLog testLog;

    public SetupAssistant(SeleniumInstance testInstance, TestLog log) {
        this.seleniumInstance=testInstance;
        this.testLog=log;
    }

    public void setupStandardAircraftFromEquipmentForm(String fuel) {
        setUpAircraftFromEquipmentForm(  fuel,
                                6,
                             4,
                                            Boolean.FALSE,
                                            Boolean.FALSE,
                                            Boolean.FALSE);
    }

    public void setUpAircraftFromEquipmentForm(String   fuel,
                                               int      numMissiles,
                                               int      numDumbBombs,
                                               Boolean  reconPod,
                                               Boolean  intelliBomb,
                                               Boolean  externalFuelTanks) {
        //Assumes you are already where you need to be on equipment page
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();

        String testDescription="Setting up an aircraft with,";

        if(!fuel.equals("")) {
            equipPage.setInitialFuelField(fuel);
            testDescription += "<br>Add fuel of " + fuel + "kg";
        }

        if(numMissiles!=0) {
            equipPage.setNumMissiles(numMissiles);
            testDescription += "<br>Number of missiles: " + numMissiles;
        }

        if(numDumbBombs!=0) {
            equipPage.setNumDumbBomb(numDumbBombs);
            testDescription += "<br>Number of dumb bombs: " + numDumbBombs;
        }

        if(reconPod){
            equipPage.selectReconPod();
            testDescription += "<br>Recon pod";
        }

        if(intelliBomb){
            equipPage.selectIntelliBomb();
            testDescription += "<br>Intelli bomb";
        }

        if(externalFuelTanks){
            equipPage.selectFuelTank();
            testDescription += "<br>Fuel tanks";
        }

        testLog.addScreenshot(seleniumInstance.getDriver(), "setUpAircraftFromEquipmentForm", testDescription);

    }

    public void selectLoadFromEquipmentForm() {
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.clickLoadButton();
    }


    // Select apply / dive / cruise - all assume on the Status form
    public void selectCruiseFromStatusForm() {
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        statusPage.setCruise();
    }

    public void selectClimbFromStatusForm() {
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        statusPage.setClimb();
    }

    public void selectDiveFromStatusForm() {
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        statusPage.setDive();
    }

    public void applyCruiseFromCruiseForm(String speed, String distance) {
        CruisePage cruisePage = new CruisePage(seleniumInstance);
        cruisePage.waitForPage();
        cruisePage.setSpeed(speed);
        cruisePage.setDistance(distance);
        cruisePage.applyCruiseButton();
        cruisePage.waitForPageToVanish();
    }

    public void applyDiveFromDiveForm(String altitude) {
        DivePage divePage = new DivePage(seleniumInstance);
        divePage.waitForPage();
        divePage.setDiveAltitude(altitude);
        divePage.clickApplyButton();
        divePage.waitForPageToVanish();
    }

    public void applyClimbFromClimbForm(String altitude) {
        ClimbPage climbPage = new ClimbPage(seleniumInstance);
        climbPage.waitForPage();
        climbPage.setClimbAltitude(altitude);
        climbPage.clickApplyButton();
        climbPage.waitForPageToVanish();
    }

    public void setHeightFromStatusForm(String newAltitude) {
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        int currentAltitude = statusPage.getHeight();
        int intNewAlt = Integer.parseInt(newAltitude);

        if (currentAltitude > intNewAlt) {
            this.selectDiveFromStatusForm();
            this.applyDiveFromDiveForm(newAltitude);
        } else {
            this.selectClimbFromStatusForm();
            this.applyClimbFromClimbForm(newAltitude);
        }
    }

    public String getErrorMessage() {
        StatusPage statusPage = new StatusPage(seleniumInstance);
        return statusPage.getErrorMessage();
    }

}
