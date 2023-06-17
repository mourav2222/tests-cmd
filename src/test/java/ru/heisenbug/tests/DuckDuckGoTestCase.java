package ru.heisenbug.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.heisenbug.extension.GoogleTestExtension;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

class DuckDuckGoTestCase extends BaseCase {

    private final Logger logger = LoggerFactory.getLogger(DuckDuckGoTestCase.class);

    @Test
    void userCanSearchKeyword() {

        logger.info("Class name: {}", DuckDuckGoTestCase.class.getName());
        logger.info("Browser (userCanSearchKeyword): {}", GoogleTestExtension.browser.orElse("undefined_browser"));

        if(GoogleTestExtension.browser.isPresent()) {
            Configuration.browser = GoogleTestExtension.browser.get();
        }
        open("https://duckduckgo.com");
        $(By.name("q")).val("selenide heisenbug")
                .pressEnter();

        $$(".react-results--main [data-testid='result']").shouldHave(sizeGreaterThan(5));
//        $(".react-results--main [data-testid='result']").shouldHave(text("Moscow"));
        $$(".react-results--main [data-testid='result']").get(0).shouldHave(text("heisenbug.ru"));

        sleep(5000);
    }

}
