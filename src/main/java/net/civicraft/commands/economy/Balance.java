package net.civicraft.commands.economy;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

public class Balance extends Command {
    public Balance() {
        super("balance");
        setDefaultExecutor((sender, context) -> {
            if (sender instanceof Player player) {
                //Return balance
                player.sendMessage("Balance: $");
            }
        });

        var targetPlayer = ArgumentType.Entity("target").onlyPlayers(true);
    }
}
