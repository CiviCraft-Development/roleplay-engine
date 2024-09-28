package net.civicraft.commands.company;

import net.minestom.server.command.builder.Command;

public class JobBoard extends Command {
    public JobBoard() {
        super("jobboard");
        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("Job Board");
        });
    }
}
