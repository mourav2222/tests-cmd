package ru.heisenbug;

import ru.heisenbug.service.TestLauncherService;

public class TestLauncher {

    public static void main(String[] args) {

        TestLauncherService testLauncherService = new TestLauncherService();
        testLauncherService.executeTestPlan("chrome");

    }


}
