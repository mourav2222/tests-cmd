package ru.heisenbug.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.heisenbug.extension.GoogleTestExtension;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

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
        open("https://duckduckgo.com");
        $(By.name("q")).val("selenide heisenbug")
                .pressEnter();

        $$(".react-results--main [data-testid='result']").shouldHave(sizeGreaterThan(5));
        $(".react-results--main [data-testid='result']").shouldHave(text("Moscow"));

        sleep(5000);
    }

}
