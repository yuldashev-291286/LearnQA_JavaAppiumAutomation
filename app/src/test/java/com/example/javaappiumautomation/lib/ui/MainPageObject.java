package com.example.javaappiumautomation.lib.ui;

import static org.openqa.selenium.ScreenOrientation.LANDSCAPE;
import static org.openqa.selenium.ScreenOrientation.PORTRAIT;

import android.os.FileUtils;

import com.example.javaappiumautomation.lib.Platform;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver){
        this.driver = driver;
    }

    // Ex7*: Поворот экрана.
    // Функция, которая возвращает положение экрана в портретный режим.
    public ScreenOrientation returnScreenOrientationPortrait(ScreenOrientation screenOrientation){

        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;

            if (screenOrientation.equals(LANDSCAPE)){
                driver.rotate(PORTRAIT);
            }

        } else {
            System.out.println("Method returnScreenOrientationPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

        return PORTRAIT;

    }


    // Ex2: Создание метода.
    // Необходимо написать функцию, которая проверяет наличие ожидаемого текста у элемента.
    // Предлагается назвать ее assertElementHasText.
    // На вход эта функция должна принимать локатор элемент, ожидаемый текст и текст ошибки,
    // который будет написан в случае, если элемент по этому локатору не содержит текст, который мы ожидаем.
    public String assertElementHasText(String locator, String expectedText, String errorMessage) {

        waitForElementPresent(locator, errorMessage, 5);
        By by = this.getLocatorByString(locator);
        String text = null;

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
            WebElement searchWikipedia = driver.findElement(by);
            text = searchWikipedia.getAttribute("text");

        } else if (Platform.getInstance().isMW()){
            WebElement searchWikipedia = driver.findElement(by);
            text = searchWikipedia.getAttribute("placeholder");

        }

        if (expectedText.equals(text)) {
            return text;
        }else {
            return errorMessage;
        }

    }

    // Ex6: Тест: assert title
    // Написать тест, который открывает статью и убеждается, что у нее есть элемент title.
    // Важно: тест не должен дожидаться появления title, проверка должна производиться сразу.
    // Если title не найден - тест падает с ошибкой. Метод можно назвать assertElementPresent.
    public String assertElementPresent(String locator, String error_message) {
        By by = this.getLocatorByString(locator);

        WebElement webElement = null;
        try{
            webElement = driver.findElement(by);
        }catch (NoSuchElementException e){
            System.out.println("Element not found on page.");
            Assert.fail(error_message);
            return error_message;
        }

        if (webElement.isDisplayed()){
            System.out.println("Element found on page.");
            return "Element found on page.";
        }else {
            return error_message;
        }

    }


    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds){

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = null;

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){

            wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.withMessage(error_message + "\n");

        } else if (Platform.getInstance().isMW()) {

            wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.withMessage(error_message + "\n");

        }
        return wait.until(
                ExpectedConditions
                        .presenceOfElementLocated(by));

    }

    public WebElement waitForElementPresent(String locator, String error_message){
        return waitForElementPresent(locator, error_message, 5);

    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;

    }

    public WebElement waitForElementByIdAndClick(String id, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresentById(id, error_message, timeoutInSeconds);
        element.click();
        return element;

    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;

    }

    public WebElement waitForElementPresentById(String id, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.
                        presenceOfElementLocated(by)
        );
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.
                        invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void swipeUp(int timeOfSwipe){

        if (driver instanceof AppiumDriver){
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);

            action
                    .press(x, start_y)
                    .waitAction(timeOfSwipe)
                    .moveTo(x, end_y)
                    .release()
                    .perform();

        } else {
            System.out.println("Method swipeUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    public void swipeUpQuick(){
        swipeUp(200);
    }

    public void scrollWebPageUp(){

        if (Platform.getInstance().isMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes){
        int already_swiped = 0;
        WebElement element = this.waitForElementPresent(locator, error_message);

        while (!this.isElementLocatedOnTheScreen(locator)){
            scrollWebPageUp();
            ++already_swiped;

            if (already_swiped > max_swipes){
                Assert.assertTrue(error_message, element.isDisplayed());
            }

        }

    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes){

        By by = this.getLocatorByString(locator);

        int already_swiped = 0;
        while(driver.findElements(by).size() == 0){

            if (already_swiped > max_swipes){
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }

    }

    public void swipeUpToFindElementNew(String locator, String error_message, int max_swipes) {

        By by = this.getLocatorByString(locator);

        int alreadySwiped = 0;
        WebElement element = driver.findElement(by);
        int elementY = element.getLocation().getY();
        int screenHeight = driver.manage().window().getSize().height;

        while (elementY > screenHeight) { // Пока элемент не в границах экрана
            if (alreadySwiped >= max_swipes) {
                throw new NoSuchElementException("Не найден элемент: " + error_message);
            }
            swipeUpQuick();
            elementY = driver.findElement(by).getLocation().getY(); // Обновляем Y после свайпа
            alreadySwiped++;
        }

    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes){

        int already_swiped = 0;

        while (!this.isElementLocatedOnTheScreen(locator)){
            if (already_swiped > max_swipes){
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;

        }

    }

    public boolean isElementLocatedOnTheScreen(String locator){

        int element_location_by_y = this
                .waitForElementPresent(locator, "Cannot find element by locator", 5)
                .getLocation()
                .getY();

        if (Platform.getInstance().isMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());

        }

        int screen_size_by_y = driver.manage().window().getSize().getHeight();

        return element_location_by_y < screen_size_by_y;

    }

    public void clickElementToTheRightUpperCorner(String locator, String error_message){

        if (driver instanceof AppiumDriver){
            WebElement element = this.waitForElementPresent(locator + "/..", error_message);
            int right_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            int width = element.getSize().getWidth();

            int point_to_click_x = (right_x + width) - 3;
            int point_to_click_y = middle_y;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.tap(point_to_click_x, point_to_click_y).perform();

        } else {
            System.out.println("Method clickElementToTheRightUpperCorner() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    public void swipeElementToLeft(String locator, String error_message){

        if (driver instanceof AppiumDriver){
            WebElement element = waitForElementPresent(locator, error_message, 10);

            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();

            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.press(right_x, middle_y);
            action.waitAction(300);

            if (Platform.getInstance().isAndroid()){
                action.moveTo(left_x, middle_y);
            }else {
                int offset_x = (-1 * element.getSize().getWidth());
                action.moveTo(offset_x, 0);
            }

            action.release();
            action.perform();

        } else {
            System.out.println("Method swipeElementToLeft() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    public int getAmountOfElements(String locator){

        int numberOfElements = 0;

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
            By by = this.getLocatorByString(locator);
            List<WebElement> elements = driver.findElements(by);
            numberOfElements = elements.size();

        } else if (Platform.getInstance().isMW()) {
            By by = this.getLocatorByString(locator);
            List<WebElement> elements = driver.findElements(by);
            numberOfElements = elements.size();

        }
        return numberOfElements;

    }

    public boolean isElementPresent(String locator){
        return getAmountOfElements(locator) > 0;
    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts){
        int current_attempts = 0;
        boolean need_more_attempts = true;

        while (need_more_attempts){
            try {
                this.waitForElementAndClick(locator, error_message, 1);
                need_more_attempts = false;

            } catch (Exception e){
                if (current_attempts > amount_of_attempts){
                    this.waitForElementAndClick(locator, error_message, 1);

                }
            }
            ++current_attempts;
        }

    }

    public void assertElementNotPresent(String locator, String error_message){
        By by = this.getLocatorByString(locator);

        int amount_of_elements = getAmountOfElements(locator);

        if (amount_of_elements > 0){
            String default_message = "An element '" + by.toString() + "' supposed to be not present.";
            throw new AssertionError(default_message + " " + error_message);


        }

    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds){
        WebElement webElement = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return webElement.getAttribute(attribute);
    }

    private By getLocatorByString(String locator_with_type){ // locator_with_type = type:locator
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")){
            return By.xpath(locator);

        } else if (by_type.equals("id")){
            return By.id(locator);

        } else if (by_type.equals("css")){
            return By.cssSelector(locator);

        }
        else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }

    }

/*
    public String takeScreenshot(String name){

        TakesScreenshot takesScreenshot = (TakesScreenshot)this.driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name + "_screenshot.png";
        try {
            FileUtils.copy(source, new File(path));
            System.out.println("The screenshot was taken: " + path);
        } catch (Exception e){
            System.out.println("Cannot take screenshot. Error: " + e.getMessage());

        }
        return path;

    }

    //@Attachment
    public static byte[] screenshot(String path){

        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(path));

        } catch (IOException e) {
            System.out.println("Cannot get bytes from screenshot. Error: " + e.getMessage());

        }
        return bytes;

    }

 */


}
