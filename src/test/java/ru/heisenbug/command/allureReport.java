package ru.heisenbug.command;

import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
@CommandLine.Command(
        name = "allureReport", mixinStandardHelpOptions = true,
        description = "Run allure report in the browser"
)
public class allureReport implements Runnable {

    @CommandLine.Option(
            names = {"--results", "-r"},
            description = "allure-results directory",
            paramLabel = "ALLURE-RESULTS-DIR"
    )
    protected File allureResulstsDir = new File("build/allure-results");

    @Override
    public void run() {
        try {
            runUnsafe();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    public void runUnsafe() throws IOException {

        Process ps;

        String[] cmd = {"/home/mike/test/guitester/allure-2.22.4/bin/allure", "serve", allureResulstsDir.getAbsolutePath()};

        try {
            ps = Runtime.getRuntime().exec(cmd);
            String sf1=String.format("Proccess ID: %d", ps.pid());
            System.out.println(sf1);
//            Thread.sleep(90000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
