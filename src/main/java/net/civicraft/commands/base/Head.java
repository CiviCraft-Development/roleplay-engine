package net.civicraft.commands.base;

import net.kyori.adventure.text.Component;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandExecutor;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.display.ItemDisplayMeta;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.tag.Tag;
import net.minestom.server.utils.entity.EntityFinder;

import java.util.UUID;

public class Head extends Command {
    public Head() {
        super("head");
        setDefaultExecutor((sender, context) -> {
          if (sender instanceof Player) {
              Player player = (Player) sender;
              UUID playerUUID = player.getUuid();
              ItemStack head = ItemStack.builder(Material.PLAYER_HEAD).customName(Component.text(player.getDisplayName() + "'s head")).build();
              player.getInventory().addItemStack(head);
          }
        });

        var target = ArgumentType.Entity("target").onlyPlayers(true);

        addSyntax((sender, context) -> {
            if (sender instanceof Player player) {
                EntityFinder finder = context.get(target);
                Player targetPlayer = finder.findFirstPlayer(player);
                UUID targetUUID = targetPlayer.getUuid();
                ItemStack head = ItemStack.builder(Material.PLAYER_HEAD).customName(Component.text(targetPlayer.getDisplayName() + "'s head")).build();
                player.getInventory().addItemStack(head);
            }
        }, target);
    }
}
