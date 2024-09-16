package net.civicraft.commands.base;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;

public class Teleport extends Command {
    public Teleport() {
        super("teleport", "tp");
        setDefaultExecutor((sender, context) -> {
            if (sender instanceof Player player) {
                player.sendMessage("Incorrect usage.");
            }
        });

        var target = ArgumentType.Entity("player").onlyPlayers(true);
        var here = ArgumentType.Literal("here");
        var posX = ArgumentType.Integer("x");
        var posY = ArgumentType.Integer("y");
        var posZ = ArgumentType.Integer("z");

        // /teleport <player>
        addSyntax((sender, context) -> {
           if (sender instanceof Player player) {
               EntityFinder finder = context.get(target);
               Player targetP = finder.findFirstPlayer(player);
               Pos position = targetP.getPosition();
               if (targetP == player) {
                   player.sendMessage("You cannot teleport to yourself.");
               } else if (targetP != null) {
                   player.teleport(position);
                   player.sendMessage("Teleported to " + targetP.getName());
               } else {
                   player.sendMessage("Player not found");
               }
           }
        }, target);

        // /teleport x, y, z
        addSyntax((sender, context) -> {
            if (sender instanceof Player player) {
                Integer x = context.get(posX);
                Integer y = context.get(posY);
                Integer z = context.get(posZ);
                player.teleport(new Pos(x, y, z));
            }
        }, posX, posY, posZ);

        // /teleport here <Player>
        addSyntax((sender, context) -> {
            if (sender instanceof Player player) {
                EntityFinder finder = context.get(target);
                Player targetP = finder.findFirstPlayer(player);
                Pos position = player.getPosition();
                if (targetP == player) {
                    player.sendMessage("You cannot teleport yourself to yourself.");
                } else if (targetP != null) {
                    targetP.teleport(position);
                }
            }
        }, here, target);
    }
}
