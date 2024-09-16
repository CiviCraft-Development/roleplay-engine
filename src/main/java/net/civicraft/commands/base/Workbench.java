package net.civicraft.commands.base;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;

public class Workbench extends Command {
    public Workbench() {
        super("workbench");
        setDefaultExecutor((sender, context) -> {
            Player player = (Player) sender;
            player.sendMessage("Specify a workbench.");
        });

        var furnaceTable = ArgumentType.Literal("Furnace");
        var craftingTable = ArgumentType.Literal("Crafting_Table");
        var anvilTable = ArgumentType.Literal("Anvil");


        // Furnace
        addSyntax((sender, context) -> {
            if (sender instanceof Player player) {
                Inventory furnaceInv = new Inventory(InventoryType.FURNACE, "Furnace");
                player.openInventory(furnaceInv);
                player.sendMessage(BaseMsg.OPEN_WB);
            }
        }, furnaceTable);

        // Crafting table
        addSyntax((sender, context) -> {
            if (sender instanceof Player player) {
                Inventory craftingInv = new Inventory(InventoryType.CRAFTING, "Crafting Table");
                player.openInventory(craftingInv);
                player.sendMessage(BaseMsg.OPEN_WB);
            }
        }, craftingTable);

        // Anvil
        addSyntax((sender, context) -> {
            if (sender instanceof Player player) {
                Inventory anvilInv = new Inventory(InventoryType.ANVIL, "Anvil");
                player.openInventory(anvilInv);
                player.sendMessage(BaseMsg.OPEN_WB);
            }
        }, anvilTable);


    }
}
