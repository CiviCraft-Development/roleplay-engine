package net.civicraft.commands.roleplay;

import net.minestom.server.command.builder.Command;

public class RPScenario extends Command {
    public RPScenario() {
        super("scenario");
        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("Scen.");
        });
    }
}
