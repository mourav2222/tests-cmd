package ru.heisenbug.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.heisenbug.extension.GoogleTestExtension;

@ExtendWith(GoogleTestExtension.class)
public class GoogleTest {

    private final Logger logger = LoggerFactory.getLogger(GoogleTest.class);

//     don't work
//    @BeforeAll
//    public void setup() {
//        logger.info("Class name (BeforeAll): {}", GoogleTest.class.getName());
//        logger.info("Browser (BeforeAll): {}", GoogleTestExtension.browser.orElse("undefined_browser"));
//    }


    @Test
    public void userCanSearchKeyword() {

        logger.info("Class name: {}", GoogleTest.class.getName());
        logger.info("Browser (userCanSearchKeyword): {}", GoogleTestExtension.browser.orElse("undefined_browser"));

        // default browser chrome
        if(GoogleTestExtension.browser.isPresent()) {
            Configuration.browser = GoogleTestExtension.browser.get();
        }

        Configuration.browserSize = "1600x900";

        Selenide.open("https://duckduckgo.com");
        Selenide.$(By.name("q")).val("selenide heisenbug")
                .pressEnter();

        Selenide.$$(".react-results--main [data-testid='result']").shouldHave(CollectionCondition.sizeGreaterThan(5));
        Selenide.$$(".react-results--main [data-testid='result']").get(0).shouldHave(Condition.text("heisenbug.ru"));

        Selenide.sleep(5000);
    }

}
