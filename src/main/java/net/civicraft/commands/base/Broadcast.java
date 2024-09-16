package net.civicraft.commands.base;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;


public class Broadcast extends Command {

    public Broadcast() {
        super("broadcast");


        setDefaultExecutor((sender, context) -> sender.sendMessage("You must specify a message to broadcast."));

        var message = ArgumentType.StringArray("message");

        addSyntax((sender, context) -> {
            String[] messageArray = context.get(message);
            StringBuilder messageBuilder = new StringBuilder();
            for (String s : messageArray) {
                messageBuilder.append(s).append(" ");
            }
            String finalMessage = "[Broadcast] " + messageBuilder;
            Audiences.players().sendMessage(Component.text(finalMessage).color(NamedTextColor.RED));
        }, message);
    }


}
