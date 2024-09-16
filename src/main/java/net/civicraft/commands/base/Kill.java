package net.civicraft.commands.base;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;

import java.util.List;

public class Kill extends Command {
    public Kill() {
        super("kill");

        setDefaultExecutor((sender, context) -> {
            Player player = (Player) sender;
            player.sendMessage("You must specify a target.");
        });

        var killTarget = ArgumentType.Entity("target").onlyPlayers(true).singleEntity(true);
        addSyntax((sender, context) -> {
            Player player = (Player) sender;
            EntityFinder targetFinder = context.get(killTarget);
            List<Entity> entities = targetFinder.find(player);
            if (entities.isEmpty() || !(entities.getFirst() instanceof Player target)) {
                player.sendMessage("No valid player found to kill.");
                return;
            }

            target.kill();
            player.sendMessage("You have killed " + target.getUsername() + ".");
        }, killTarget);
    }
}
