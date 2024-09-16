package net.civicraft.commands.health;

import net.minestom.server.command.builder.Command;

public class Cure extends Command {
    public Cure() {
        super("cure");
        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("You have been cured of any disease.");
        });
    }
}
