package net.civicraft.commands.base;

import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;

public class God extends Command {

    public God() {
        super("god");

        setDefaultExecutor((sender, context) -> {
            Player player = (Player) sender;
            if (player.isInvulnerable() && player.hasPermission("base.god")) {
                player.setInvulnerable(false);
                player.sendMessage("God mode disabled.");
            } else {
                player.setInvulnerable(true);
                player.sendMessage("God mode enabled.");
            }
        });
    }
}
