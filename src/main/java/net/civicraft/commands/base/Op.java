package net.civicraft.commands.base;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;

public class Op extends Command {
    public Op() {
        super("op");
        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("Usage: /op <player> <op>");
        });

        var targetPlayer = ArgumentType.Entity("target").onlyPlayers(true);

        addSyntax((sender, context) -> {
            if (sender instanceof Player player) {
                EntityFinder finder = context.get(targetPlayer);
                Player target = finder.findFirstPlayer(player);
                if (target != null) {
                    target.setPermissionLevel(4);
                    Component message = Component.text("You have OP'd " + target.getUsername()).color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD);
                    player.sendMessage("You have OP'd " + target.getUsername());
                } else {
                    player.sendMessage("No valid player found to OP. Did you spell their name right?");
                }

            }



        }, targetPlayer);
    }
}
