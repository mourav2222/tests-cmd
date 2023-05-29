package ru.heisenbug.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.heisenbug.extension.GoogleTestExtension;

import static com.codeborne.selenide.Selenide.*;

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
        open("https://duckduckgo.com");
        $(By.name("q")).val("selenide heisenbug")
                .pressEnter();

        sleep(5000);
    }

}
