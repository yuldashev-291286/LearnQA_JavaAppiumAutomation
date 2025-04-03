package com.example.javaappiumautomation;

import static org.openqa.selenium.ScreenOrientation.LANDSCAPE;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.ui.MainPageObject;
import com.example.javaappiumautomation.lib.ui.SkipPageObject;

public class AndroidTests extends CoreTestCase {

    private MainPageObject mainPageObject;
    private SkipPageObject skipPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        mainPageObject = new MainPageObject(driver);
        skipPageObject = new SkipPageObject(driver);
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

        mainPageObject.waitForElementPresent(
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
        mainPageObject.swipeElementToLeft(
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
        mainPageObject.swipeElementToLeft(
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
        mainPageObject.swipeElementToLeft(
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
        mainPageObject.waitForElementPresent(
                By.id(acceptButton),
                "There are no buttons to go to the main page",
                5
        );

        String rejectButton = "org.wikipedia:id/rejectButton";
        mainPageObject.waitForElementPresent(
                By.id(rejectButton),
                "There are no buttons to go to the main page",
                5
        );

        // 3. Нажимает кнопку: "Accept" на Android и “Get started” на iOS в конце Onboarding.
        mainPageObject.waitForElementAndClick(
                By.id(acceptButton),
                "There are no buttons to go to the main page",
                5
        );

        Thread.sleep(3000);

        // 4. Убеждается в отображении Главного экрана.
        mainPageObject.waitForElementPresent(
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

        skipPageObject.waitSkipAndClick();

        mainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        Thread.sleep(5000);

        mainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' text",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' text",
                5

        );

        Thread.sleep(2000);

        mainPageObject.assertElementPresent(
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

        skipPageObject.waitSkipAndClick();

        mainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        // 1) Написать тест, который делает поиск по какому-то слову. Например, JAVA.
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Java",
                "Cannot find search input",
                5
        );

        Thread.sleep(5000);

        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
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

        skipPageObject.waitSkipAndClick();

        mainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        // 1) Ищет какое-то слово.
        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Java",
                "Cannot find search input",
                5
        );

        Thread.sleep(5000);

        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Nothing found when searching for the word",
                5
        );

        // 2) Убеждается, что найдено несколько статей.
        List<WebElement> webElementList = driver.findElements(
                By.id("org.wikipedia:id/page_list_item_title"));
        webElementList.addAll(webElementList);

        if (webElementList.isEmpty()) {
            Assert.fail("Nothing found when searching for the word");
        }

        // 3) Отменяет поиск.
        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        // 4) Убеждается, что результат поиска пропал.
        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );

        mainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Search Wikipedia']"),
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

        skipPageObject.waitSkipAndClick();

        mainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        String text = mainPageObject.assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "Unable to find text in the 'Wikipedia Search' box"
        );

        assertEquals("Search Wikipedia", text);

    }


    // Ex7*: Поворот экрана.
    //
    // Appium устроен так, что может сохранить у себя в памяти поворот экрана, который использовался в предыдущем тесте,
    // и начать новый тест с тем же поворотом. Мы написали тест на поворот экрана, и он может сломаться до того,
    // как положение экрана восстановится. Следовательно, если мы запустим несколько тестов одновременно,
    // последующие тесты будут выполняться в неправильном положении экрана, что может привести к незапланированным проблемам.
    //
    // Как нам сделать так, чтобы после теста на поворот экрана сам экран всегда оказывался в правильном положении,
    // даже если тест упал в тот момент, когда экран был наклонен?
    @Test
    public void testScreenRotation() throws InterruptedException {

        // Для примера. Из предыдущего теста вернулась ориентация экрана LANDSCAPE.
        driver.rotate(LANDSCAPE);
        System.out.println(driver.getOrientation());

        // В начале теста ставим положение экрана в портретный режим.
        mainPageObject.returnScreenOrientationPortrait(driver.getOrientation());
        System.out.println(driver.getOrientation());

        mainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' button",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find 'Skip' button",
                5
        );

        mainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

    }


}
