package net.civicraft.commands.music;

/*
This code has been borrowed from emortal's NBStom repository
 */

import net.civicraft.item.MusicDisc;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemComponent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import java.util.Arrays;

public class MusicInv {

    private static MusicInv INSTANCE;
    private final Inventory inventory;

    public MusicInv() {
        Component inventoryTitle = Component.text("Music Discs", NamedTextColor.BLACK);
        Inventory inventory = new Inventory(InventoryType.CHEST_6_ROW, inventoryTitle);

        ItemStack[] itemStacks = new ItemStack[inventory.getSize()];
        Arrays.fill(itemStacks, ItemStack.AIR);

        var i = 10;
        for (MusicDisc disc : MusicDisc.values()) {
            if ((i + 1) % 9 == 0) i += 2;

            itemStacks[i] = ItemStack.builder(disc.getMaterial())
                    .set(ItemComponent.ITEM_NAME, Component.text(disc.getDescription(), NamedTextColor.AQUA).decoration(TextDecoration.ITALIC, false))
                    .set(ItemComponent.HIDE_ADDITIONAL_TOOLTIP)
                    .build();

            i++;
        }


        itemStacks[40] = ItemStack.builder(Material.BARRIER)
                .set(ItemComponent.ITEM_NAME, Component.text("Stop", NamedTextColor.RED, TextDecoration.BOLD))
                .build();

        inventory.copyContents(itemStacks);


        inventory.addInventoryCondition((player, slot, clickType, inventoryConditionResult) -> {
            inventoryConditionResult.setCancel(true);

            if (inventoryConditionResult.getClickedItem() == ItemStack.AIR) return;

            if (slot == 40) {
                Music.stop(player);
                return;
            }

            MusicDisc nowPlayingDisc = MusicDisc.fromMaterial(inventoryConditionResult.getClickedItem().material());

            Music.playDisc(player, nowPlayingDisc.getShortName());
        });

        this.inventory = inventory;
    }

    public static Inventory getInventory() {
        if (INSTANCE == null) {
            INSTANCE = new MusicInv();
        }

        return INSTANCE.inventory;
    }
}
