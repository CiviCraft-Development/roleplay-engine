package net.civicraft.commands.actions;

import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;

public class Sit extends Command {
    public Sit() {
        super("sit");
        setDefaultExecutor((sender, context) -> {
            if (sender instanceof Player player) {
                player.setPose(Entity.Pose.SITTING);
                player.sendMessage("You are sitting.");
            }
        });
    }
}
