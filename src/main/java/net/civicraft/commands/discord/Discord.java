package net.civicraft.commands.discord;

import net.minestom.server.command.builder.Command;

public class Discord extends Command {
    public Discord() {
        super("discord");
        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("<Discord link>");
        });
    }
}
