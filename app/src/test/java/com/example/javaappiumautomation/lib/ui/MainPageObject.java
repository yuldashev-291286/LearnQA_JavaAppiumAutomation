package com.example.javaappiumautomation.lib.ui;

import static org.openqa.selenium.ScreenOrientation.LANDSCAPE;
import static org.openqa.selenium.ScreenOrientation.PORTRAIT;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }

    // Ex7*: Поворот экрана.
    // Функция, которая возвращает положение экрана в портретный режим.
    public ScreenOrientation returnScreenOrientationPortrait(ScreenOrientation screenOrientation){
        if (screenOrientation.equals(LANDSCAPE)){
            driver.rotate(PORTRAIT);
        }
        return PORTRAIT;

    }


    // Ex2: Создание метода.
    // Необходимо написать функцию, которая проверяет наличие ожидаемого текста у элемента.
    // Предлагается назвать ее assertElementHasText.
    // На вход эта функция должна принимать локатор элемент, ожидаемый текст и текст ошибки,
    // который будет написан в случае, если элемент по этому локатору не содержит текст, который мы ожидаем.
    public String assertElementHasText(By by, String expectedText, String errorMessage) {

        waitForElementPresent(by, errorMessage, 5);

        WebElement searchWikipedia = driver.findElement(by);
        String text = searchWikipedia.getAttribute("text");

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
    public String assertElementPresent(By by, String error_message) {

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


    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions
                        .presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String error_message){

        return waitForElementPresent(by, error_message, 5);

    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds){

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;

    }

    public WebElement waitForElementByIdAndClick(String id, String error_message, long timeoutInSeconds){

        WebElement element = waitForElementPresentById(id, error_message, timeoutInSeconds);
        element.click();
        return element;

    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds){

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;

    }

    public WebElement waitForElementPresentById(String id, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds){

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds){

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void swipeUp(int timeOfSwipe){

        TouchAction action = new TouchAction(driver);
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

    }

    public void swipeUpQuick(){
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes){

        int already_swiped = 0;
        while(driver.findElements(by).size() == 0){

            if (already_swiped > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }

    }

    public void swipeUpToFindElementNew(By by, String error_message, int max_swipes) {

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


    public void swipeElementToLeft(By by, String error_message){

        WebElement element = waitForElementPresent(by, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();

        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }

    public int getAmountOfElements(By by){
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String error_message){

        int amount_of_elements = getAmountOfElements(by);

        if (amount_of_elements > 0){
            String default_message = "An element '" + by.toString() + "' supposed to be not present.";
            throw new AssertionError(default_message + " " + error_message);


        }

    }

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds){
        WebElement webElement = waitForElementPresent(by, error_message, timeoutInSeconds);
        return webElement.getAttribute(attribute);
    }


}
