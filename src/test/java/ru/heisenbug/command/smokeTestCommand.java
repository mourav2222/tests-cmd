package ru.heisenbug.command;

import picocli.CommandLine;
import ru.heisenbug.service.TestLauncherService;

@CommandLine.Command(
        name = "smoketest", mixinStandardHelpOptions = true,
        description = "Run smoke tests in the browser"
)
public class smokeTestCommand implements Runnable {

    @CommandLine.Option(
            names = {"--browser"},
            description = "Browser name or default is chrome"
    )
    protected String browser;

    @Override
    public void run() {
        try {
            runUnsafe();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void runUnsafe() throws Exception {

        final TestLauncherService testLauncherService = new TestLauncherService();
        testLauncherService.executeTestPlan(browser);

    }

}
