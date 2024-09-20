package net.civicraft.commands.technology;

import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;

public class Phone extends Command {
    public Phone() {
        super("phone");

        /*
        /phone

        Command that manually opens a phone. This should not be used in live roleplay, but moreso for development purposes to ensure that phones are operational without having to access a phone item.
         */
        setDefaultExecutor((sender, context) -> {
            if (sender instanceof Player player) {

            }
        });
    }
}
