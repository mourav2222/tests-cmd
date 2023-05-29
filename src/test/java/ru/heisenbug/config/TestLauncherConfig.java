package ru.heisenbug.config;

public enum TestLauncherConfig {
    BROWSER("testlauncher.browser"),
    SM_SERVER("testlauncher.smserver"),
    SELENOID("testlauncher.selenoid");


    private final String parameter;

    private TestLauncherConfig(final String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }

}
