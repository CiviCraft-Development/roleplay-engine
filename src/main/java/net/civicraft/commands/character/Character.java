package net.civicraft.commands.character;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;

public class Character extends Command {
    public Character() {
        super("character");
        setDefaultExecutor((sender, context) -> {

        });

        var create = ArgumentType.Literal("create");
        var edit = ArgumentType.Literal("edit");
        var transfer = ArgumentType.Literal("transfer");
        var character = ArgumentType.Literal("character");

        /*
        /character create

        Begins the character creation process for a player. Checks if the player meets the criteria for creating a new character before launching the process.

        Criteria:
        (1) Available space: Players are granted a certain number of character slots. On the initial join, players will be automatically prompted to create their first character within the tutorial. In the future, they may create a new character. Character slots are awarded with purchase from the server store (this uses in-game currency, not real life currency) or may be manually added by administrators.
        (2) Permission to create: The player must have permission to create a character. Generally, all players meet this item by default, but other players may not. The reasons why the player would not meet this item may be varied.

        Players will be required to provide the following information to create a character:
        (1) Name: The character must have a name. The name provided is checked against a list of words purposefully excluded from the server. This is to guarantee that the name chosen is appropriate for all members of the community.
        (2) Age: The character must have an age. The age will be required to be between 1-100. Character stature will be affected by this selection. Selections outside the provided range will not be accepted.
        (3) Role: The character must have a role. Players are provided with a number of pre-made default roles that can be begun at any time. Certain roles are locked behind permission with the player being responsible to apply to them. Players may also create custom roles.

        Players will be encouraged, but not required, to provide the following information to create a charcater:
        (1) Description: The character may have a description. This would describe how the character should appear to others. This will have no affect on the character's literal appearance to others. This description can be accessed at any time by running the command /profile <Player> <Character> which wil return information about a character.
         */
        addSyntax((sender, context) -> {

        }, create, character);

        /*
        /character edit <Character>

        This command allows players to change information about their characters. Not all information about a character may be changed given the fact that in real life, you may not simply change yourself in a flash.
         */
        addSyntax((sender, context) -> {

        }, edit, character);

        /*
        /character transfer <Character> <Player>

        Transfers a character profile to another player.

        In order to be able to transfer a character, the player must meet the following criteria:
        (1) 24 Hours of Playtime: To prevent abuse of our character creation system, players must have accrued 24 hours of playtime to be able to transfer their character to another account. The recipient of the transfer must also meet this criteria.
        (2) No Punishment History Related to Character System Abuse: Individuals who have a punishment history related to abusing the character system will not be allowed to transfer or receive characters.

        Upon transfer, character data will be exchanged from the original owner to the transfer recipient. When the recipient chooses to switch to this character, they will obtain the following:
        (1) Character: The recipient will now be able to use the transferred character in roleplay scenarios.
        (2) Character posessions: The inventory of the character upon transfer will be moved to the recipient's account. All belongings such as properties, wearables, and other applicable items will be transferred.
         */
        addSyntax((sender, context) -> {

        }, transfer, character);
    }
}
