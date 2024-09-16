package net.civicraft.commands.base;

import net.minestom.server.command.builder.Command;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;

public class Spawn extends Command {
    public Spawn() {
        super("spawn");
        setDefaultExecutor((sender, context) -> {
            if (sender instanceof Player player) {
                player.teleport(new Pos(0, 46, 0));
            }
        });
    }
}
