package ru.heisenbug.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import ru.heisenbug.extension.GoogleTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(GoogleTestExtension.class)
public class DuckDuckGoTest {

    private final Logger logger = LoggerFactory.getLogger(DuckDuckGoTest.class);

    @Test
    public void userCanSearchKeyword() {

        logger.info("Class name: {}", DuckDuckGoTest.class.getName());
        logger.info("Browser (userCanSearchKeyword): {}", GoogleTestExtension.browser.orElse("undefined_browser"));

        if(GoogleTestExtension.browser.isPresent()) {
            Configuration.browser = GoogleTestExtension.browser.get();
        }
        Selenide.open("https://duckduckgo.com");
        Selenide.$(By.name("q")).val("selenide heisenbug")
                .pressEnter();

        Selenide.sleep(5000);
    }

}
