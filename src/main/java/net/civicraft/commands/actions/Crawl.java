package net.civicraft.commands.actions;

import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;

public class Crawl extends Command {
    public Crawl() {
        super("crawl");
        setDefaultExecutor((sender, context) -> {
            Player player = (Player) sender;
        });
    }
}
