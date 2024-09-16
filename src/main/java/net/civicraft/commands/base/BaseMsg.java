package net.civicraft.commands.base;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;

public class BaseMsg {
    public static final TextColor PrimaryBC = TextColor.color(74, 73, 72);
    public static final TextColor SecondaryBC = TextColor.color(145, 144, 141);
    public static final Component Prefix = Component.text("[Server] ").color(PrimaryBC);

    public static final Component NO_FIRE = Prefix.append(Component.text("You are not on fire.").color(SecondaryBC));
    public static final Component OPEN_WB = Prefix.append(Component.text("You have opened a workbench.").color(SecondaryBC));
}
