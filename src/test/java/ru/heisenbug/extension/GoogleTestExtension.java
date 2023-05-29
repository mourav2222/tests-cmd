package ru.heisenbug.extension;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.heisenbug.config.TestLauncherConfig;
import ru.heisenbug.tests.GoogleTest;

import java.util.Optional;

public class GoogleTestExtension implements BeforeAllCallback {

    private final Logger logger = LoggerFactory.getLogger(GoogleTestExtension.class);

    public static Optional<String> browser = Optional.empty();
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {

        logger.info("Class name: {}", GoogleTest.class.getName());

        browser = context.getConfigurationParameter(TestLauncherConfig.BROWSER.getParameter());
        logger.info("Browser (beforeAll): {}", GoogleTestExtension.browser.orElse("undefined_browser"));

    }
}
