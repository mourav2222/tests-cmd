package ru.heisenbug.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import ru.heisenbug.extension.GoogleTestExtension;

import static com.codeborne.selenide.Selenide.*;

@ExtendWith(GoogleTestExtension.class)
public class GoogleTest {


    @Test
    public void userCanSearchKeyword() {

        System.out.println("Browser (userCanSearchKeyword): " + GoogleTestExtension.browser.orElse("undefined_browser"));

        Configuration.browser = "firefox";
        open("https://duckduckgo.com");
        $(By.name("q")).val("selenide heisenbug")
                .pressEnter();

        sleep(5000);
    }

}
