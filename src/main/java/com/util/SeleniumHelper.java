package com.util;

import com.google.common.io.Files;
import com.google.inject.spi.ProviderInstanceBinding;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.*;

public class SeleniumHelper {

    public static void clickByXpath(WebDriver driver, String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    public static void clickByCss(WebDriver driver, String locator) {
        driver.findElement(By.cssSelector(locator)).click();
    }

    public static void clickByName(WebDriver driver, String locator) {
        driver.findElement(By.name(locator)).click();
    }

    public static void clickById(WebDriver driver, String locator) {
        driver.findElement(By.id(locator)).click();
    }

    public static void clickByTagName(WebDriver driver, String locator) {
        driver.findElement(By.tagName(locator)).click();
    }

    public static String getTextByXpath(WebDriver driver, String locator) {
        return driver.findElement(By.xpath(locator)).getText();
    }

    public static String getTextByCss(WebDriver driver, String locator) {
        return driver.findElement(By.cssSelector(locator)).getText();
    }

    public static String getTextByName(WebDriver driver, String locator) {
        return driver.findElement(By.name(locator)).getText();
    }

    public static String getTextById(WebDriver driver, String locator) {
        return driver.findElement(By.id(locator)).getText();
    }

    public static String getTextByTagName(WebDriver driver, String locator) {
        return driver.findElement(By.tagName(locator)).getText();
    }

    public static ArrayList<Object> getTextFromWebElementsXpath(WebDriver driver, String locator) {
        List<WebElement> elements = driver.findElements(By.xpath(locator));
        ArrayList<Object> text = new ArrayList<Object>();

        for (WebElement element : elements) {
            text.add(element.getText());
        }

        return text;
    }

    public static ArrayList<Object> getTextFromWebElementsCss(WebDriver driver, String locator) {
        List<WebElement> elements = driver.findElements(By.cssSelector(locator));
        ArrayList<Object> text = new ArrayList<Object>();

        for (WebElement element : elements) {
            text.add(element.getText());
        }

        return text;
    }

    public static void clickWebElementsByXpath(WebDriver driver, String locator) {
        List<WebElement> elements = driver.findElements(By.xpath(locator));

        for (WebElement element : elements) {
            element.click();
            driver.navigate().back();
        }
    }

    public static void clickWebElementsByCss(WebDriver driver, String locator) {
        List<WebElement> elements = driver.findElements(By.cssSelector(locator));

        for (WebElement element : elements) {
            element.click();
            driver.navigate().back();
        }
    }

    public static ArrayList<String> getExpectedData(String[] expectedData) {
        ArrayList<String> data = new ArrayList<String>();

        for (String ex : expectedData) {
            data.add(ex);
        }

        return data;

    }

    public static void compareValue(ArrayList<String> actualData, ArrayList<String> expectedData) {
        Iterator<String> actual = actualData.iterator();
        Iterator<String> expected = expectedData.iterator();

        while (actual.hasNext() && expected.hasNext()) {
            if (actual.next().toString().equalsIgnoreCase(expected.next().toString())) {
                System.out.println("Verified " + actual.next() + " " + expected.next());
            } else {
                System.out.println("Can not verify "+actual.next()+" "+expected.next());
            }

        }
    }

    public static int getPriceByXpath(WebDriver driver, String locator) {
        String actualText = driver.findElement(By.xpath(locator)).getText();
        int price = Integer.parseInt(actualText.replace('$', ' '));
        return price;
    }

    public static int getPriceByCss(WebDriver driver, String locator) {
        String actualText = driver.findElement(By.cssSelector(locator)).getText();
        int price = Integer.parseInt(actualText.replace('$', ' '));
        return price;
    }

    public void verifyPrice(int actualPrice, int expectedPrice) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualPrice, expectedPrice);
    }

    public static String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public static String getUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public static Properties fileReader(String path) {
        FileInputStream file = null;
        Properties properties = null;

        try {
            file = new FileInputStream(new File(path));
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public static String getDateAndTime() {
        SimpleDateFormat date = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date currentDate = new Date();
        return date.format(currentDate);
    }

    public static void takeScreenShot(WebDriver driver)  {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(SrcFile, new File("ScreenShots/pic_" + getDateAndTime() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WebElement waitForElementXpath(WebDriver driver ,String locator, int sec) {
        WebDriverWait wait = new WebDriverWait(driver, sec);
        WebElement element = driver.findElement(By.xpath(locator));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForElementCss(WebDriver driver ,String locator, int sec) {
        WebDriverWait wait = new WebDriverWait(driver, sec);
        WebElement element = driver.findElement(By.xpath(locator));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void jsExecutorClickByXpath(WebDriver driver, String locator) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(locator)));
    }

    public static void jsExecutorClickByCss(WebDriver driver, String locator) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(locator)));
    }

    public static void scrollByWithJSExecutor(WebDriver driver, int pixel) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,"+pixel+")");
    }



}
