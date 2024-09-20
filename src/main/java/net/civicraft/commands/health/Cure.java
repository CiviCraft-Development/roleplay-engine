package net.civicraft.commands.health;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;

public class Cure extends Command {
    public Cure() {
        /*
        /cure

        Automatically cures a player of any health related diseases.

        /cure != /heal -- /heal fills a players health bar. This treats diseases that players may be inflicted with.
         */
        super("cure");
        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("You have been cured of any disease.");
        });

        var target = ArgumentType.Entity("target").onlyPlayers(true);
        addSyntax((sender, context) -> {

        }, target);
    }
}
