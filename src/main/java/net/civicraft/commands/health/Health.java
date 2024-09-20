package net.civicraft.commands.health;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;

public class Health extends Command {
    public Health() {
        super("health");
        // NOTE TO SELF: THESE NEED TO BE REMOVED WHEN THE INITIAL VERSION IS RELEASED.
        double temp = 36.8;
        boolean isC = true;
        boolean hasDisease = true;
        String diseases = "Encephalitis";


        /*
        /health

        Returns the health status of a player. The health status will show the player's temperature, any diseases they may have, and a general "health" grade. The following health grades are available: Good (no diseases, normal temp), Feverish (no diseases, irregular temp), Ill (disease, temp varies)
         */
        setDefaultExecutor((sender, context) -> {
            if (sender instanceof Player player) {
                player.sendMessage(Component.text("Health").color(NamedTextColor.DARK_RED).decorate(TextDecoration.BOLD));
                player.sendMessage(Component.text("Health Status: <Good | Feverish | Ill>"));
                // Sample execution for testing, temporary code.
                if (isC) {
                    if (temp < 36.1) {
                        player.sendMessage(Component.text("Temperature: " + temp).color(NamedTextColor.BLUE));
                    } else if (temp > 37.2) {
                        player.sendMessage(Component.text("Temperature: " + temp).color(NamedTextColor.RED));
                    } else {
                        player.sendMessage(Component.text("Temperature: " + temp).color(NamedTextColor.WHITE));
                    }
                } else {
                    if (temp < 97) {
                        player.sendMessage(Component.text("Temperature: " + temp).color(NamedTextColor.BLUE));
                    } else if (temp > 99) {
                        player.sendMessage(Component.text("Temperature: " + temp).color(NamedTextColor.RED));
                    } else {
                        player.sendMessage(Component.text("Temperature: " + temp).color(NamedTextColor.WHITE));
                    }
                }
                if (hasDisease) {
                    player.sendMessage(Component.text("Diseases: " + diseases));
                }
            }
        });

        var target = ArgumentType.Entity("target").onlyPlayers(true);

        /*
        /health <target>

        Returns the health status of a target player. This may be restricted to only medical professionals.
         */
        addSyntax((sender, context) -> {
            if (sender instanceof Player player) {
                EntityFinder finder = context.get(target);
                Player targetplayer = finder.findFirstPlayer(player);
                assert targetplayer != null;
                player.sendMessage(Component.text(targetplayer.getDisplayName() + "'s Health").color(NamedTextColor.DARK_RED).decorate(TextDecoration.BOLD));
                player.sendMessage(Component.text("Health Status: <Good | Feverish | Ill>"));
            }

        }, target);
    }
}
