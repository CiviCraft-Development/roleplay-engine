package net.civicraft.commands.health;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandExecutor;
import net.minestom.server.command.builder.arguments.ArgumentType;

/*
Returns only the player's temperature
 */
public class Temperature extends Command {
    public Temperature() {
        super("temperature");
        setDefaultExecutor((sender, context) -> {
            // Returns sender's temp

            sender.sendMessage("temp");
        });

        // Target for /temperature <target> to view another player's temp
        var target = ArgumentType.Entity("target").onlyPlayers(true);
    }
}
