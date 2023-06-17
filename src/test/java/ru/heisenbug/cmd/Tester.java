package ru.heisenbug.cmd;

import picocli.CommandLine;

public class Tester {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new MainCommand()).execute(args);
        System.exit(exitCode);
    }

}