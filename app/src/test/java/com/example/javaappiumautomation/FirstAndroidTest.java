package com.example.javaappiumautomation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class FirstAndroidTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception{

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("platformVersion","8");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C://Git//LearnQA_AutomationMobileApplications//src//main//apks//org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void firstTest(){

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

/*        WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");
        element_to_init_search.click();

        WebElement element_to_enter_search_line = waitForElementPresentByXpath(
                "//*[contains(@text,'Search')]",
                "Cannot find search input"
        );
        element_to_enter_search_line.sendKeys("Java");
*/
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15);

    }

    @Test
    public void testCancelSearch(){

        waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

/*        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
*/
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );

    }

    @Test
    public void testCompareArticleTitle(){

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' text",
                15
        );

/*        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' text",
                5
        );
*/
        WebElement title_element = waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Java (programming language)']"),
                "Cannot find article title",
                5
        );

        String article_title = title_element.getAttribute("text");

        assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title
        );


    }

    // Ex4*: Тест: Проверка слов в поиске.
    // 1) Написать тест, который делает поиск по какому-то слову. Например, JAVA.
    // 2) Затем убеждается, что в каждом результате поиска есть это слово.
    @Test
    public void testCheckWordsInSearch() throws InterruptedException {

        waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );

        // 1) Написать тест, который делает поиск по какому-то слову. Например, JAVA.
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search')]"),
                "Java",
                "Cannot find search input",
                5
        );

        Thread.sleep(5000);

        waitForElementPresent(
                By.id("org.wikipedia:id/search_results_container"),
                "Nothing found when searching for the word",
                5
        );

        // 2) Затем убеждается, что в каждом результате поиска есть это слово.
        List<WebElement> webElementList = driver.findElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text,'Java')]"));
        webElementList.addAll(webElementList);

        if (webElementList.isEmpty()) {
            Assert.fail("Nothing found when searching for the word");
        }

    }


    // Ex3: Тест: Отмена поиска.
    // Написать тест, который:
    // 1) Ищет какое-то слово.
    // 2) Убеждается, что найдено несколько статей.
    // 3) Отменяет поиск.
    // 4) Убеждается, что результат поиска пропал
    @Test
    public void testCheckCancelSearch() throws InterruptedException {

        waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );

        // 1) Ищет какое-то слово.
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search')]"),
                "Java",
                "Cannot find search input",
                5
        );

        Thread.sleep(5000);

        waitForElementPresent(
                By.id("org.wikipedia:id/search_results_container"),
                "Nothing found when searching for the word",
                5
        );

        // 2) Убеждается, что найдено несколько статей.
        List<WebElement> webElementList = driver.findElements(
                By.xpath("(//*[@resource-id='org.wikipedia:id/page_list_item_container'])"));
        webElementList.addAll(webElementList);

        if (webElementList.isEmpty()) {
            Assert.fail("Nothing found when searching for the word");
        }

        // 3) Отменяет поиск.
        waitForElementPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        // 4) Убеждается, что результат поиска пропал.
        waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );


    }


    // Ex2: Создание метода.
    // Также, необходимо написать тест, который проверяет, что поле ввода для поиска статьи содержит текст
    // (в разных версиях приложения это могут быть тексты "Search..." или "Search Wikipedia",
    // правильным вариантом следует считать тот, которые есть сейчас).
    // Очевидно, что тест должен использовать написанный ранее метод.
    @Test
    public void testCheckForExpectedText(){

        waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );

        String text = assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "Unable to find text in the 'Wikipedia Search' box"
        );

        assertEquals("Search Wikipedia", text);

        //WebElement searchWikipedia = driver.findElement(By.xpath("//*[contains(@text,'Search Wikipedia')]"));
        //String text = searchWikipedia.getAttribute("text");
        //System.out.println(text);
        //assertEquals("Search Wikipedia", text);

    }


    // Ex2: Создание метода.
    // Необходимо написать функцию, которая проверяет наличие ожидаемого текста у элемента.
    // Предлагается назвать ее assertElementHasText.
    // На вход эта функция должна принимать локатор элемент, ожидаемый текст и текст ошибки,
    // который будет написан в случае, если элемент по этому локатору не содержит текст, который мы ожидаем.
    private String assertElementHasText(By by, String expectedText, String errorMessage) {

        waitForElementPresent(by, errorMessage, 5);

        WebElement searchWikipedia = driver.findElement(by);
        String text = searchWikipedia.getAttribute("text");

        if (expectedText.equals(text)) {
            return text;
        }else {
            return errorMessage;
        }

    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions
                        .presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message){

        return waitForElementPresent(by, error_message, 5);

    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds){

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;

    }

    private WebElement waitForElementByIdAndClick(String id, String error_message, long timeoutInSeconds){

        WebElement element = waitForElementPresentById(id, error_message, timeoutInSeconds);
        element.click();
        return element;

    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds){

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;

    }

    private WebElement waitForElementPresentById(String id, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds){

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds){

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }


}
