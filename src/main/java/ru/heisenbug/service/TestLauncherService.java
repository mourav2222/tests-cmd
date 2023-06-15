package ru.heisenbug.service;

import org.junit.platform.engine.discovery.ClassNameFilter;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.heisenbug.config.TestLauncherConfig;

import java.io.PrintWriter;

public  class TestLauncherService {
    final private Launcher launcher;
    private final Logger logger = LoggerFactory.getLogger(TestLauncherService.class);

    public TestLauncherService() {


        launcher = LauncherFactory.create();
    }

    public void executeTestPlan(String browser) {

        if(browser == null) {
            browser = "chrome";
        }

        logger.info("Browser (executeTestPlan): {}", browser);

        SummaryGeneratingListener summaryGeneratingListener = new SummaryGeneratingListener();
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .configurationParameter(TestLauncherConfig.BROWSER.getParameter(), browser)
                .selectors(DiscoverySelectors.selectPackage("ru.heisenbug.tests"))
                .filters(ClassNameFilter.includeClassNamePatterns("ru\\.heisenbug\\.tests\\.Google.*"))
                // .filters(includeClassNamePatterns("ru\\.heisenbug\\.tests\\.XXGoogle.*"))
                // .filters(includeClassNamePatterns("ru\\.heisenbug\\.tests\\..*"))
                .build();
        launcher.execute(request, summaryGeneratingListener);

        try(PrintWriter printWriter = new PrintWriter(System.out)) {
            summaryGeneratingListener.getSummary().printTo(printWriter);
        }


    }
}
