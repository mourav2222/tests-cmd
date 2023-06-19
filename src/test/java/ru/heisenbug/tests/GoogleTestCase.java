package ru.heisenbug.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.heisenbug.extension.GoogleTestExtension;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

class GoogleTestCase extends BaseCase {

    private final String GLOBAL_PARAMETER = "global parameter";
    private final Logger logger = LoggerFactory.getLogger(GoogleTestCase.class);

//     don't work
//    @BeforeAll
//    public void setup() {
//        logger.info("Class name (BeforeAll): {}", GoogleTest.class.getName());
//        logger.info("Browser (BeforeAll): {}", GoogleTestExtension.browser.orElse("undefined_browser"));
//    }


    @Test
    @DisplayName("Human-readable test name")
    @Description("Some detailed test description")
    @Severity(SeverityLevel.CRITICAL)
    void userCanSearchKeyword() {

        logger.info("Class name: {}", GoogleTestCase.class.getName());
        logger.info("Browser (userCanSearchKeyword): {}", GoogleTestExtension.browser.orElse("undefined_browser"));

        // default browser chrome
        GoogleTestExtension.browser.ifPresent(s -> Configuration.browser = s);
        open("https://duckduckgo.com");
        $(By.name("q")).val("selenide heisenbug")
                .pressEnter();

        step2("xytin", "mydak");

        sleep(5000);
    }

    @Step("Type {user} / {password}.")
    public void step2(String user, String password) {
        $$(".react-results--main [data-testid='result']").shouldHave(sizeGreaterThan(5));
//        $(".react-results--main [data-testid='result']").shouldHave(text("Moscow"));
        $$(".react-results--main [data-testid='result']").get(0).shouldHave(text("heisenbug.ru"));

    }

    @Test
    @Flaky
    void userCanSearchKeyword2() {
        userCanSearchKeyword();
    }

    @Test
    @Story("Story-001")
    void test1() {
        Configuration.timeout = 1;
        open("http://todomvc.com/examples/angularjs/#/");

        step1();
        step2();
        step3();
    }
    @Step("Step 1")
    public void step1() {
//        Allure.step(String.format("Step 1 [%s]", GLOBAL_PARAMETER));
        refresh();
        refresh();
    }

    @Step("Step 2")
    public void step2() {
//        Allure.step(String.format("Step 2 [%s]", GLOBAL_PARAMETER));
        refresh();
    }

    @Step("Step 3")
    public void step3() {
//        Allure.step(String.format("Step 3 [%s]", GLOBAL_PARAMETER));
        refresh();
    }
}
