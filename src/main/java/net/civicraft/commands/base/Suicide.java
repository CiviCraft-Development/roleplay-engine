package net.civicraft.commands.base;

import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;

public class Suicide extends Command {
    public Suicide() {
        super("suicide");
        setDefaultExecutor((sender, context) -> {
            if (!(sender instanceof Player player)) {
                sender.sendMessage("Only players can use this command.");
            } else {
                player.kill();

            }
        });
    }
}
