package net.civicraft.commands.actions;

import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerStopSneakingEvent;
import net.minestom.server.event.GlobalEventHandler;

public class Lay extends Command {

    public Lay(GlobalEventHandler globalEventHandler) {
        super("lay");

        setDefaultExecutor((sender, context) -> {
            if (sender instanceof Player player) {
                // Check if the player is already laying
                if (player.isSneaking()) {
                    player.sendMessage("You are already laying down.");
                    return;
                }

                // Set the player as sneaking (simulating laying down)
                player.setSneaking(true);
                player.teleport(player.getPosition().add(0, 0, 0)); // Adjust to simulate laying

                player.sendMessage("You are now laying down.");

                // Register listener to handle standing up
                registerSneakListener(globalEventHandler, player);
            } else {
                sender.sendMessage("Only players can use this command.");
            }
        });
    }

    private void registerSneakListener(GlobalEventHandler globalEventHandler, Player player) {
        // Listener for when the player stands up
        globalEventHandler.addListener(PlayerStopSneakingEvent.class, event -> {
            if (event.getPlayer().equals(player)) {
                player.teleport(player.getPosition().add(0, 1.5, 0)); // Adjust back to standing position
                player.sendMessage("You are now standing up.");
            }
        });
    }
}
