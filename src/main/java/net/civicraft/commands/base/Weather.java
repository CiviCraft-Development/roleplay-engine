package net.civicraft.commands.base;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;

public class Weather extends Command {

    public Weather() {
        super("weather");

        var weatherType = ArgumentType.Word("type")
                .from("clear", "rain", "thunder");

        setDefaultExecutor((sender, context) -> {
            if (sender instanceof Player player) {
                player.sendMessage("You must specify a weather type.");
            }
        });

        addSyntax((sender, context) -> {
            if (!(sender instanceof Player player)) {
                sender.sendMessage("Only players can use this command.");
                return;
            }

            String type = context.get(weatherType);
            Instance instance = player.getInstance();

            if (instance == null) {
                player.sendMessage("No instance found.");
                return;
            }
            if ("clear".equalsIgnoreCase(type)) {
                instance.setWeather(net.minestom.server.instance.Weather.CLEAR);
                player.sendMessage("The weather has been set to clear.");
            } else if ("rain".equalsIgnoreCase(type)) {
                instance.setWeather(net.minestom.server.instance.Weather.RAIN);
                player.sendMessage("The weather has been set to rain.");
            } else if ("thunder".equalsIgnoreCase(type)) {
                instance.setWeather(net.minestom.server.instance.Weather.THUNDER);
                player.sendMessage("The weather has been set to thunder.");
            } else {
                player.sendMessage("Invalid weather type. Valid types: clear, rain, thunder.");
            }
        }, weatherType);
    }
}
