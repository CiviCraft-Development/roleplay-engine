package net.civicraft.commands.music;

/*
This code has been borrowed from emortal's NBStom repository
 */
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;
import net.minestom.server.tag.Tag;

public class Loop extends Command {

    public static final Tag<Boolean> loopTag = Tag.Boolean("loop");

    public Loop() {
        super("loop");

        setDefaultExecutor((sender, context) -> {
            if (!(sender instanceof Player player)) return;

            if (player.hasTag(loopTag)) {
                player.removeTag(loopTag);
                player.sendMessage(Component.text("Current song will no longer loop!", NamedTextColor.GREEN));
            } else {
                player.setTag(loopTag, true);
                player.sendMessage(Component.text("Current song will now loop!", NamedTextColor.GREEN));
            }
        });
    }


}
