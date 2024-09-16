package net.civicraft.commands.base;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;

import java.util.List;

public class Ext extends Command {
    public Ext() {
        super("ext", "extinguish");

        setDefaultExecutor((sender, context) -> {
            if (!(sender instanceof Player player)) {
                sender.sendMessage("Only players can use this command.");
                return;
            } else {
                if (player.isOnFire()) {
                    player.setFireTicks(0);
                } else {
                    player.sendMessage(BaseMsg.NO_FIRE);
                }
            }
        });

        var fireTarget = ArgumentType.Entity("target").onlyPlayers(true).singleEntity(true);

        addSyntax((sender, context) -> {
                Player player = (Player) sender;
                EntityFinder targetFinder = context.get(fireTarget);
                List<Entity> entities = targetFinder.find(player);
                if (entities.isEmpty() || !(entities.getFirst() instanceof Player target)) {
                    player.sendMessage("No valid player found to extinguish.");
                    return;
                }
                target.setFireTicks(0);
                player.sendMessage("You have saved " + target.getUsername() + " from the fire!");
        }, fireTarget);


    }
}
