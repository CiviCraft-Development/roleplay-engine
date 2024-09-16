package net.civicraft.commands.company;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class CompanyMsg {
    public static final TextColor PrimaryCM = TextColor.color(163, 127, 7);
    public static final TextColor SecondaryCM = TextColor.color(255, 222, 115);
    public static final Component Prefix = Component.text("[Company] ").color(PrimaryCM).decorate(TextDecoration.BOLD);

    public static final Component VIEW_COMPANY = Component.text("").append(Prefix).append(Component.text("You have viewed a company.")).color(SecondaryCM);
    public static final Component NEW_COMPANY = Component.text("").append(Prefix).append(Component.text("You have created a new company.")).color(SecondaryCM);
    public static final Component INVALID_USAGE = Component.text("").append(Prefix).append(Component.text("Invalid command usage.")).color(SecondaryCM);


}
