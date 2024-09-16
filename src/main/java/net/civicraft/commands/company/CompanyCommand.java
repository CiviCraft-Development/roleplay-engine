package net.civicraft.commands.company;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;

/**
 * /company
 *
 * Root command for all company interactions
 */
public class CompanyCommand extends Command {
    public CompanyCommand() {
        super("company");

        setDefaultExecutor((sender, context) -> {
            if (sender instanceof Player player) {
                player.sendMessage("Company command ran.");
            }
        });

        var newCommandArg = ArgumentType.Literal("new");
        var viewCompany = ArgumentType.Literal("view");
        var hireArg = ArgumentType.Literal("hire");
        var hireTarget = ArgumentType.Entity("hirePlayer").onlyPlayers(true);
        var fireArg = ArgumentType.Literal("fire");
        var fireTarget = ArgumentType.Entity("firePlayer").onlyPlayers(true);

        // /company new
        addSyntax((sender, context) -> {
            if (sender instanceof Player player) {
                player.sendMessage(CompanyMsg.NEW_COMPANY);
            }
        }, newCommandArg);

        // /company view
        addSyntax((sender, context) -> {
            if (sender instanceof Player player) {
                player.sendMessage(CompanyMsg.VIEW_COMPANY);
            }
        }, viewCompany);

        // /company hire <Player>
        addSyntax((sender, context) -> {
            if (sender instanceof Player player) {
                EntityFinder finder = context.get(hireTarget);
                Player targetPlayer = finder.findFirstPlayer(player);

                if (targetPlayer != null) {
                    player.sendMessage("You have hired " + targetPlayer.getUsername());
                } else {
                    player.sendMessage(CompanyMsg.INVALID_USAGE);
                }
            }
        }, hireArg, hireTarget);

        addSyntax((sender, context) -> {
            if (sender instanceof Player player) {
                EntityFinder finder = context.get(fireTarget);
                Player targetPlayer = finder.findFirstPlayer(player);
                if (targetPlayer != null && targetPlayer != player) {
                    player.sendMessage("You have fired " + targetPlayer.getUsername());
                } else if (targetPlayer.equals(player)) {
                    player.sendMessage("You cannot fire yourself.");
                } else {
                    player.sendMessage(CompanyMsg.INVALID_USAGE);
                }
            }
        }, fireArg, fireTarget);
    }
}
