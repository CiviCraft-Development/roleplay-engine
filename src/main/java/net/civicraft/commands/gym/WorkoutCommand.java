package net.civicraft.commands.gym;

import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;

/*
/workout

Opens up a GUI and allows players to choose a workout. This will then begin an animation.
 */
public class WorkoutCommand extends Command {
    public WorkoutCommand() {
        super("workout");

        // This code is ran when there are no arguments provided or the arguments provided do not match intended arguments
        setDefaultExecutor((((commandSender, commandContext) -> {
            Player player = (Player) commandSender;
            player.sendMessage("Workout command ran.");
        })));
    }
}
