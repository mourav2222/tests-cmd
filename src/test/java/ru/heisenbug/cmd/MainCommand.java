package ru.heisenbug.cmd;

import picocli.CommandLine;
import ru.heisenbug.command.smokeTestCommand;

@CommandLine.Command(
        name = "guitester",
        subcommands = {
            smokeTestCommand.class
        }
        )
public class MainCommand implements Runnable {

    @Override
    public void run() {
        new CommandLine(new MainCommand()).usage(System.out);
    }

}
