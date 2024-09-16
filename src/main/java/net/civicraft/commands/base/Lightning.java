package net.civicraft.commands.base;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandExecutor;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;

public class Lightning extends Command {
    public Lightning() {
        super("lightning");
        setDefaultExecutor((sender, context) -> {
           Player player = (Player) sender;
           Instance instance = player.getInstance();
        });

    }
}
