package net.civicraft.commands.economy;

import net.civicraft.commands.company.CompanyMsg;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;

public class Balance extends Command {
    public Balance() {
        super("balance");
        setDefaultExecutor((sender, context) -> {
            if (sender instanceof Player player) {
                //Return balance
                player.sendMessage("Balance: $");
            }
        });

        var target = ArgumentType.Entity("target").onlyPlayers(true);

        addSyntax((sender, context) -> {
            if (sender instanceof Player player) {
                EntityFinder finder = context.get(target);
                Player targetPlayer = finder.findFirstPlayer(player);

                if (targetPlayer != null) {
                    player.sendMessage(targetPlayer.getDisplayName() + "'s balance: $<balance>");
                } else {
                    player.sendMessage("Invalid target");
                }
            }
        }, target);
    }
}
