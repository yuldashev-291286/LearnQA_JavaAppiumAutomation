package com.example.javaappiumautomation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class AndroidTests {

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

    // Занятие 4. Учебный тест №1. Swipe.
    @Test
    public void testSwipeArticle() throws InterruptedException {

        waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );

        Thread.sleep(2000);

        swipeUpToFindElement(
               By.xpath("//*[@text='Read a random article from Wikipedia']"),
               "Cannot find the end of the article",
                20
       );


    }

    // Занятие 4. Учебный тест №2.
    @Test
    public void saveFirstArticleToMyList(){

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

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );



    }

    // Занятие 4. Учебный тест №3.
    @Test
    public void testAmountOfNotEmptySearch() throws InterruptedException {

        waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );

        String search_line = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );

        Thread.sleep(5000);

        waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Nothing found when searching for the word",
                5
        );

        String search_result_locator = "//android.widget.LinearLayout[@resource-id='org.wikipedia:id/page_list_item_container']";

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results !",
                amount_of_search_results > 0
        );


    }

    // Занятие 4. Учебный тест №4.
    @Test
    public void testAmountOfEmptySearch() throws InterruptedException {

        waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );

        String search_line = "test45";
        //String search_line = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );

        Thread.sleep(5000);

        String empty_result_label = "//*[@text='No results found']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                5
        );

        assertElementNotPresent(
                By.xpath(search_line),
                "We have found same results by request " + search_line + "."
        );


    }

    // Занятие 4. Учебный тест №5.
    @Test
    public void testChangeScreenOrientationOnSearchResults() {

        waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );

        String title_before_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[contains(@text,'Wikipedia')]"),
                "text",
                "Cannot find title of article",
                5
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[contains(@text,'Wikipedia')]"),
                "text",
                "Cannot find title of article",
                5
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[contains(@text,'Wikipedia')]"),
                "text",
                "Cannot find title of article",
                5
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );



    }

    // Занятие 4. Учебный тест №6.
    @Test
    public void testCheckSearchArticleInBackground() throws InterruptedException {

        waitForElementPresent(
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

        Thread.sleep(5000);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' text",
                5
        );

        driver.runAppInBackground(5);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find article after returning from background",
                5
        );


    }

    // Занятие 4. ДЗ 1. Ex5: Тест: Сохранение двух статей либо Онбординг.
    // Написать тест, который:
    // 1. Свайпами перелистывает экраны Onboarding.
    // 2. При перелистывании экрана проверяет ожидаемый Title.
    // 3. Нажимает кнопку: "Accept" на Android и “Get started” на iOS в конце Onboarding.
    // 4. Убеждается в отображении Главного экрана.
    @Test
    public void testSaveTwoArticlesOrOnboarding() throws InterruptedException {

        String locatorPrimaryTextView = "org.wikipedia:id/primaryTextView";

        waitForElementPresent(
                By.id(locatorPrimaryTextView),
                "Wikipedia home page not found",
                5
        );

        // 2. При перелистывании экрана проверяет ожидаемый Title.
        String title = driver
                .findElement(By.id(locatorPrimaryTextView))
                .getAttribute("text");

        assertEquals("The Free Encyclopedia\n…in over 300 languages", title);

        // 1. Свайпами перелистывает экраны Onboarding.
        swipeElementToLeft(
                By.id(locatorPrimaryTextView),
                "Failed to proceed to next page"
        );

        Thread.sleep(1000);
        System.out.println(title);

        // 2. При перелистывании экрана проверяет ожидаемый Title.
        title = driver
                .findElement(By.id(locatorPrimaryTextView))
                .getAttribute("text");

        assertEquals("New ways to explore", title);

        Thread.sleep(1000);
        System.out.println(title);

        // 1. Свайпами перелистывает экраны Onboarding.
        swipeElementToLeft(
                By.id(locatorPrimaryTextView),
                "Failed to proceed to next page"
        );

        // 2. При перелистывании экрана проверяет ожидаемый Title.
        title = driver
                .findElement(By.id(locatorPrimaryTextView))
                .getAttribute("text");

        assertEquals("Reading lists with sync", title);

        Thread.sleep(1000);
        System.out.println(title);

        // 1. Свайпами перелистывает экраны Onboarding.
        swipeElementToLeft(
                By.id(locatorPrimaryTextView),
                "Failed to proceed to next page"
        );

        // 2. При перелистывании экрана проверяет ожидаемый Title.
        title = driver
                .findElement(By.id(locatorPrimaryTextView))
                .getAttribute("text");

        assertEquals("Send anonymous data", title);

        Thread.sleep(1000);
        System.out.println(title);
        System.out.println("\n");


        String acceptButton = "org.wikipedia:id/acceptButton";
        waitForElementPresent(
                By.id(acceptButton),
                "There are no buttons to go to the main page",
                5
        );

        String rejectButton = "org.wikipedia:id/rejectButton";
        waitForElementPresent(
                By.id(rejectButton),
                "There are no buttons to go to the main page",
                5
        );

        // 3. Нажимает кнопку: "Accept" на Android и “Get started” на iOS в конце Onboarding.
        waitForElementAndClick(
                By.id(acceptButton),
                "There are no buttons to go to the main page",
                5
        );

        Thread.sleep(3000);

        // 4. Убеждается в отображении Главного экрана.
        waitForElementPresent(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );


    }

    // Ex6: Тест: assert title
    // Написать тест, который открывает статью и убеждается, что у нее есть элемент title.
    // Важно: тест не должен дожидаться появления title, проверка должна производиться сразу.
    // Если title не найден - тест падает с ошибкой. Метод можно назвать assertElementPresent.
    @Test
    public void testOpenArticleAndMakeSureThatSheHasTitleElement() throws InterruptedException {

        // Онбординг.
        testSaveTwoArticlesOrOnboarding();

        waitForElementPresent(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

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

        Thread.sleep(5000);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' text",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' text",
                5

        );

        Thread.sleep(2000);

        assertElementPresent(
                // Передаю локатор несуществующего элемента, чтобы проверить кейс,
                // когда элемент не успел загрузиться на страницу
                By.id("org.wikipedia:id/page_title"),
                "Element not found on page."
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

    // Ex6: Тест: assert title
    // Написать тест, который открывает статью и убеждается, что у нее есть элемент title.
    // Важно: тест не должен дожидаться появления title, проверка должна производиться сразу.
    // Если title не найден - тест падает с ошибкой. Метод можно назвать assertElementPresent.
    private String assertElementPresent(By by, String error_message) {

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

    protected void swipeUp(int timeOfSwipe){

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

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){

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

    protected void swipeElementToLeft(By by, String error_message){

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

    private int getAmountOfElements(By by){
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message){

        int amount_of_elements = getAmountOfElements(by);

        if (amount_of_elements > 0){
            String default_message = "An element '" + by.toString() + "' supposed to be not present.";
            throw new AssertionError(default_message + " " + error_message);


        }

    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds){
        WebElement webElement = waitForElementPresent(by, error_message, timeoutInSeconds);
        return webElement.getAttribute(attribute);
    }


}
