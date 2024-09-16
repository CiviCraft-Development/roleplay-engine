package net.civicraft.commands.health;

import net.minestom.server.command.builder.Command;

public class Health extends Command {
    public Health() {
        super("health");
        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("Health status here.");
        });
    }
}
