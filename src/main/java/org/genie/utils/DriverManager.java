package org.genie.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    protected static AndroidDriver<MobileElement> driver;
    private static DriverManager instance;

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            synchronized (DriverManager.class) {
                if (instance == null) {
                    instance = new DriverManager();
                }
            }
        }
        return instance;
    }

    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Test");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "co.appnation.geniechat");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "co.appnation.geniechat.MainActivity");
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, desiredCapabilities);
    }

    public AndroidDriver<MobileElement> getAppiumDriver() {
        if (driver != null)
            return driver;
        return null;
    }

    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    public void installApp(String apkFilePath) {
        File app = new File(apkFilePath);
        if (app.exists()) {
            try {
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Test");
                desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
                desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14");
                URL url = new URL("http://127.0.0.1:4723/wd/hub");
                driver = new AndroidDriver(url, desiredCapabilities);
                driver.installApp(app.getAbsolutePath());
                System.out.println("APK installation completed.");
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("APK file not found.");
        }
    }

    public void uninstallApp(String packageName) {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Test");
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13");
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AndroidDriver(url, desiredCapabilities);
            driver.removeApp(packageName);
            System.out.println("Uninstall completed for package: " + packageName);
            driver.quit();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void clearAppCache() {
        driver.executeScript("mobile:shell", ImmutableMap.of("command", "pm", "args", "clear co.appnation.geniechat"));
    }

    public boolean checkOpenedApp(String expectedPackage) {
        return driver.getCurrentPackage().equals(expectedPackage);
    }
}
