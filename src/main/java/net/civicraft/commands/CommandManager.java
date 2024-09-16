package net.civicraft.commands;

import net.civicraft.commands.actions.Sit;
import net.civicraft.commands.base.*;
import net.civicraft.commands.company.CompanyCommand;
import net.civicraft.commands.discord.Discord;
import net.civicraft.commands.music.Loop;
import net.civicraft.commands.music.Music;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import java.util.List;

/*
Welcome to the command manager!

This is where I just put all the commands so that I don't flood the Main class.
 */
public class CommandManager {
    private static final List<Command> COMMANDS = List.of(
            new CompanyCommand(),
            new Broadcast(),
            new Ext(),
            new Feed(),
            new God(),
            new Heal(),
            new RepairHand(),
            new Suicide(),
            new Weather(),
            new Discord(),
            new Gamemode(),
            new Kill(),
            new Op(),
            new Sit(),
            new Spawn(),
            new Teleport(),
            new Music(),
            new Loop(),
            new Workbench(),
            new Head()
    );

    public static void registerCommands() {
        COMMANDS.forEach(command -> MinecraftServer.getCommandManager().register(command));
    }
}
