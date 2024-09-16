package net.civicraft.commands.base;

import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;

public class Heal extends Command {

    public Heal() {
        super("heal");

        setDefaultExecutor((sender, context) -> {
            Player player = (Player) sender;
            if (player.hasPermission("base.heal")) {
                player.setHealth(20);
            }

        });
    }
}
