package ru.heisenbug.service;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.*;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.heisenbug.config.TestLauncherConfig;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;

public  class TestLauncherService {
    final private Launcher launcher;
    private final Logger logger = LoggerFactory.getLogger(TestLauncherService.class);

    public TestLauncherService() {


        launcher = LauncherFactory.create();
    }

    public void executeTestPlan(String browser) {

        logger.info("Browser (executeTestPlan): {}", browser);

        SummaryGeneratingListener summaryGeneratingListener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(summaryGeneratingListener, new TestListener());

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .configurationParameter(TestLauncherConfig.BROWSER.getParameter(), browser)
                .selectors(DiscoverySelectors.selectPackage("ru.heisenbug.tests"))
                .filters(includeClassNamePatterns("ru\\.heisenbug\\.tests\\.Google.*"))
                // .filters(includeClassNamePatterns("ru\\.heisenbug\\.tests\\.XXGoogle.*"))
                // .filters(includeClassNamePatterns("ru\\.heisenbug\\.tests\\..*"))
                .build();
        launcher.execute(request, summaryGeneratingListener);

        try(PrintWriter printWriter = new PrintWriter(System.out)) {
            TestExecutionSummary testExecutionSummary = summaryGeneratingListener.getSummary();

            System.out.println("===============================================================");

            System.out.println(String.format("Found Tests: %d", testExecutionSummary.getTestsFoundCount()));
            System.out.println(String.format("Succeeded Tests: %d", testExecutionSummary.getTestsSucceededCount()));
            System.out.println(String.format("Failed Tests: %d", testExecutionSummary.getTestsFailedCount()));
            System.out.println(String.format("Aborted Tests: %d", testExecutionSummary.getTestsAbortedCount()));
            testExecutionSummary.printTo(printWriter);
        }
    }


    static class TestListener implements TestExecutionListener {

        static Map<String, String> tests = new HashMap<>();
        public static long counter = 0;

        @Override
        public void testPlanExecutionFinished(final TestPlan testPlan) {

            System.out.println("===============================================================");
            System.out.println(">>>> testPlanExecutionFinished...");

            System.out.println(String.format("Counted Tests: %d", counter));

            TestExecutionListener.super.testPlanExecutionFinished(testPlan);

            counter = 0;
            testPlan.getRoots().stream().forEach(testIdentifier -> {
                testPlan.getDescendants(testIdentifier).stream().filter(TestIdentifier::isTest).forEach(testIdentifier1 -> {
                    counter++;
                    System.out.println(">>>> " + counter + ". " + testIdentifier1.getDisplayName() + " : " + tests.get(testIdentifier1.getDisplayName()));
                });

            });
        }

        @Override
        public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {

            if(testIdentifier.isTest()) {
                counter++;
                if (testExecutionResult.getStatus() == TestExecutionResult.Status.FAILED) {
                    tests.put(testIdentifier.getDisplayName(), "FAILED");
                    System.out.println(">>>> " + counter + ". " + testIdentifier.getDisplayName() + " : FAILED");
                }
                else if (testExecutionResult.getStatus() == TestExecutionResult.Status.SUCCESSFUL) {
                    tests.put(testIdentifier.getDisplayName(), "SUCCESS");
                    System.out.println(">>>> " + counter + ". " + testIdentifier.getDisplayName() + " : SUCCESS");
                }
            }
        }
    }
}
