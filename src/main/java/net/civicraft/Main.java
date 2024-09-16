package net.civicraft;

import net.civicraft.commands.CommandManager;
import net.civicraft.discord.DiscordChatBot;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.*;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.event.item.PickupItemEvent;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.event.player.PlayerChatEvent;
import net.minestom.server.event.server.ServerListPingEvent;
import net.minestom.server.event.trait.PlayerEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.minestom.server.ping.ResponseData;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        MinecraftServer server = MinecraftServer.init();
        MinecraftServer.setBrandName("CiviCraft MS");

        // Register commands
        CommandManager.registerCommands();

        //Start up the discord bot for chat
        DiscordChatBot.init();

        // Instance management
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();

        // World generation
        instanceContainer.setGenerator(generationUnit -> {
            generationUnit.modifier().fillHeight(0, 40, Block.STONE);
            generationUnit.modifier().fillHeight(40, 43, Block.GRASS_BLOCK);
        });

        // Lighting engine
        instanceContainer.setChunkSupplier(LightingChunk::new);

        // Event management
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(0, 46, 0));
        });


        // Block breaking
        globalEventHandler.addListener(PlayerBlockBreakEvent.class, event -> {
            var material = event.getBlock().registry().material();
            if (material != null) {
                var itemStack = ItemStack.of(material);
                ItemEntity itemEntity = new ItemEntity(itemStack);
                itemEntity.setInstance(event.getInstance(), event.getBlockPosition().add(0.5, 0.5, 0.5));
                itemEntity.setPickupDelay(Duration.ofMillis(500));
            }
        });

        // Server info
        globalEventHandler.addListener(ServerListPingEvent.class, event -> {
            ResponseData responseData = event.getResponseData();
            responseData.setDescription(Component.text("")
                    .append(Component.text("          CiviCraft, but with Minestom\n")
                            .color(TextColor.color(157, 3, 252)))
                    .append(Component.text("               Now in Development!")
                            .color(TextColor.color(3, 82, 252))));
            responseData.setMaxPlayer(69);
            responseData.setOnline(10);
        });

        EventNode<Event> node = EventNode.all("all");
        node.addListener(PickupItemEvent.class, event -> {
            var itemStack = event.getItemStack();
            if (event.getLivingEntity() instanceof Player player) {
                player.getInventory().addItemStack(itemStack);
            }
        });

        EventNode<PlayerEvent> playerNode = EventNode.type("players", EventFilter.PLAYER);
        playerNode.addListener(ItemDropEvent.class, event -> {
            ItemEntity itemEntity = new ItemEntity(event.getItemStack());
            itemEntity.setInstance(event.getPlayer().getInstance(), event.getPlayer().getPosition());
            itemEntity.setVelocity(event.getPlayer().getPosition().add(0, 1, 0).direction().mul(16));
            itemEntity.setPickupDelay(Duration.ofMillis(500));
        });
        playerNode.addListener(PlayerChatEvent.class, e -> {
           Player player = e.getPlayer();
           e.setCancelled(true);
            String[] messageArray = new String[]{e.getMessage()};
            StringBuilder messageBuilder = new StringBuilder();
            for (String s : messageArray) {
                messageBuilder.append(s).append(" ");
            }
            String finalMessage = "" + messageBuilder;
            Audiences.players().sendMessage(Component.text("")
                    .append(Component.text(player.getUsername())
                            .color(TextColor.color(142, 78, 191)))
                    .append(Component.text(" >> "))
                            .color(TextColor.color(128, 99, 150))
                    .append(Component.text(finalMessage)
                            .color(TextColor.color(211, 178, 237))
                            ));
        });
        node.addChild(playerNode);

        globalEventHandler.addChild(node);

        MojangAuth.init();
        server.start("0.0.0.0", 25565);
    }
}