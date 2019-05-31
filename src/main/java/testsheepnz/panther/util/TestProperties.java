package testsheepnz.panther.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


enum BrowserType {
    CHROME, FIREFOX, CHROME_HEADLESS
}

enum MachineType {
    WINDOWS, MAC, LINUX
}

public class TestProperties {
    private BrowserType browserInUse;
    private MachineType machineInUse;
    private String driverPath;
    private String screenshotsPath;
    private String websiteToTest;
    private String websiteHomepage;
    private long maxWait;
    private int browserWidth;
    private int browserHeight;
    private Boolean shutBrowserWhenDone;

    private static final String CHROME_BROWSER_STR = "CHROME_BROWSER";
    private static final String FIREFOX_BROWSER_STR = "FIREFOX_BROWSER";
    private static final String CHROME_HEADLESS_STR = "CHROME_HEADLESS";

    private static final String WINDOWS_STR = "WINDOWS";
    private static final String LINUX_STR = "LINUX";
    private static final String MAC_STR = "MAC";

    //Error messages defined here so don't have to be defined all over the place
    public final String ERROR_MESSAGE_NO_FUEL_TANKS = "Max fuel level of 3000kg";
    public final String ERROR_MESSAGE_WITH_FUEL_TANKS = "Max fuel level of 6000kg";
    public final String ERROR_MESSAGE_ALTITUDE_NOT_A_NUMBER = "New set altitude is not a number";
    public final String ERROR_MESSAGE_INSUFFICIENT_FUEL = "Insufficient fuel for manoeuvre";

    public TestProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("test.properties");

            // load a properties file
            prop.load(input);

            // Set browser type
            this.setBrowserType(prop.getProperty("browser"));

            //Set machine type
            switch (prop.getProperty("machine_type")) {
                case MAC_STR :
                    machineInUse = MachineType.MAC;
                    driverPath = prop.getProperty("mac_driver_path");
                    break;
                case LINUX_STR:
                    machineInUse = MachineType.LINUX;
                    driverPath = prop.getProperty("linux_driver_path");
                    break;
                default:
                    machineInUse = MachineType.WINDOWS;
                    driverPath = prop.getProperty("windows_driver_path");
                    break;
            }

            screenshotsPath = prop.getProperty("screenshots");
            maxWait = Long.parseLong(prop.getProperty("max_wait"));
            setWebsiteToTest(prop.getProperty("website_under_test"));
            setWebsiteHomepage(prop.getProperty("website_homepage"));
            browserWidth= Integer.parseInt(prop.getProperty("browser_width"));
            browserHeight=Integer.parseInt(prop.getProperty("browser_height"));

            switch(prop.getProperty("shut_browser_when_done")) {
                case "false":
                    shutBrowserWhenDone = Boolean.FALSE;
                    break;
                default:
                    shutBrowserWhenDone = Boolean.TRUE;
                    break;
            }
        } catch (
        IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setBrowserType(final String newBrowser) {
        switch (newBrowser) {
            case FIREFOX_BROWSER_STR:
                browserInUse = BrowserType.FIREFOX;
                break;
            case CHROME_HEADLESS_STR:
                browserInUse = BrowserType.CHROME_HEADLESS;
                break;
            default:
                browserInUse = BrowserType.CHROME;
        }
    }

    BrowserType getBrowserType() {
        return browserInUse;
    }

    String getDriverPath() {
        return driverPath;
    }

    private void setWebsiteToTest(final String newURL) {
        websiteToTest=newURL;
    }

    String getWebsiteToTest() {
        return websiteToTest;
    }

    private void setWebsiteHomepage(final String homeURL) {
        websiteHomepage=homeURL;
    }

    public String getWebsiteHomepage() { return websiteHomepage; }

    String getScreenshotsPath() {
        return screenshotsPath;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public int getBrowserHeight() {
        return browserHeight;
    }

    public int getBrowserWidth() {
        return browserWidth;
    }

    public String getBrowserInUse() {
        switch(browserInUse) {
            case FIREFOX:
                return FIREFOX_BROWSER_STR;
            case CHROME_HEADLESS:
                return CHROME_HEADLESS_STR;
            default:
                return CHROME_BROWSER_STR;
        }
    }

    public String getMachineInUse() {
        switch(machineInUse) {
            case MAC:
                return MAC_STR;
            case LINUX:
                return LINUX_STR;
            default:
                return WINDOWS_STR;
        }
    }

    public Boolean getShutBrowserWhenDone() { return shutBrowserWhenDone; }

}


