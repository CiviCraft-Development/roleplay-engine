package net.civicraft.commands.base;

import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.display.ItemDisplayMeta;
import net.minestom.server.item.ItemStack;

public class RepairHand extends Command {

    public RepairHand() {
        super("repair");

        setDefaultExecutor((sender, context) -> {
            Player player = (Player) sender;
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.isAir()) {
                player.sendMessage("You must be holding an item to run this command.");
            } else {

            }
        });
    }
}
