package com.browsers;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserConfig {

    public static WebDriver getLocalDriver(WebDriver driver,String OS, String browserName, String url) {
        if (browserName.equalsIgnoreCase("Chrome")) {
            if (OS.equalsIgnoreCase("MAC OS")) {
                System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
            } else if (OS.equalsIgnoreCase("windows")) {
                System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
            }
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("opera")) {
            if (OS.equalsIgnoreCase("MAC OS")) {
                System.setProperty("webdriver.opera.driver", "Drivers/operadriver");
            } else if (OS.equalsIgnoreCase("windows")) {
                System.setProperty("webdriver.opera.driver", "Drivers/operadriver.exe");
            }
            driver = new OperaDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            if (OS.equalsIgnoreCase("MAC OS")) {
                System.setProperty("webdriver.safari.driver", "Drivers/safaridriver");
            } else if (OS.equalsIgnoreCase("windows")) {
                System.setProperty("webdriver.opera.driver", "Drivers/safaridriver.exe");
            }
            driver = new SafariDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            if (OS.equalsIgnoreCase("MAC OS")) {
                System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver");
            } else if (OS.equalsIgnoreCase("windows")) {
                System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
            }
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("egde")) {
            if (OS.equalsIgnoreCase("MAC OS")) {
                System.setProperty("webdriver.edge.driver", "Drivers/msedgedriver");
            } else if (OS.equalsIgnoreCase("windows")) {
                System.setProperty("webdriver.edge.driver", "Drivers/msedgedriver.exe");
            }
            driver = new EdgeDriver();
        } else {
            System.out.println(browserName + " <<Browser not found");
        }

        //
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(url);
        return driver;

    }

    public static WebDriver getCloudDriver(WebDriver driver, String browserName, String url) throws MalformedURLException {
        DesiredCapabilities cap = null;

        if (browserName.equalsIgnoreCase("chrome")) {
            cap = DesiredCapabilities.chrome();
            cap.setPlatform(Platform.ANY);

        } else if (browserName.equalsIgnoreCase("firefox")) {
            cap = DesiredCapabilities.firefox();
            cap.setPlatform(Platform.ANY);

        } else if (browserName.equalsIgnoreCase("opera")) {
            cap = DesiredCapabilities.opera();
            cap.setPlatform(Platform.ANY);

        } else if (browserName.equalsIgnoreCase("edge")) {
            cap = DesiredCapabilities.edge();
            cap.setPlatform(Platform.ANY);

        } else if (browserName.equalsIgnoreCase("safari")) {
            cap = DesiredCapabilities.safari();
            cap.setPlatform(Platform.ANY);

        } else {
            System.out.println(browserName+" << browser not found!!");
        }

        driver = new RemoteWebDriver(new URL(url), cap);
        //
        return driver;
    }

    public static void tearDown(WebDriver driver) {
        driver.quit();
    }





}
