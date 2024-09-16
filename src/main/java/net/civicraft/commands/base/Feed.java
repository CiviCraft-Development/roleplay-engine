package net.civicraft.commands.base;

import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;

public class Feed extends Command {
    public Feed() {
        super("feed");

        setDefaultExecutor((sender, context) -> {
            Player player = (Player) sender;
            if (player.hasPermission("base.feed")) {
                player.setFoodSaturation(20);
            }
        });
    }
}
