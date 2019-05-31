package testsheepnz.panther.aircraft.test;

import org.junit.*;
import org.junit.rules.TestName;
import testsheepnz.panther.util.SeleniumInstance;
import testsheepnz.panther.util.TestLog;
import testsheepnz.panther.util.TestProperties;

public class BaseTest {
    protected static SeleniumInstance seleniumInstance;
    protected Boolean testPasses;
    protected static TestProperties testProperties;
    protected static TestLog testLog;


    @Rule
    public TestName name = new TestName();

    //Before all classes
    @BeforeClass
    public static void createLogFile() {
        testProperties = new TestProperties();
        seleniumInstance = new SeleniumInstance(testProperties);
        testLog = new TestLog(testProperties);
    }

    @AfterClass
    public static void closeLogFile() {
        if (testProperties.getShutBrowserWhenDone().equals(Boolean.TRUE)) {
            seleniumInstance.quit();
        }
        testLog.closeLog();
    }

    //Actions before and after each suite of tests
    @Before
    public void setUpBrowserAndLog() {
        seleniumInstance.setBrowserSize();
        testPasses = Boolean.FALSE;
        testLog.openTable(name.getMethodName());
    }

    @After
    public void closeLogEntry() {
        String responseString = "<b>TEST PASS</b>";
        if (testPasses.equals(Boolean.FALSE)){
            responseString = "<b>TEST FAIL</b>";
        }

        this.takeScreenshot(responseString);
        testLog.closeTable();

    }

    //Actions before and after each individual test
    protected void takeScreenshot(String description) {
        testLog.addScreenshot(seleniumInstance.getDriver(), name.getMethodName(), description);
    }


}
