package net.civicraft.discord;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class DiscordChatBot extends ListenerAdapter {

    private static final Logger log = LoggerFactory.getLogger(DiscordChatBot.class);
    private static String botToken;
    private static String channelId;

    public static void init() {
        loadBotConfig();
        JDABuilder builder = JDABuilder.createDefault(botToken);
        builder.addEventListeners(new DiscordChatBot());
        builder.build();
    }

    private static void loadBotConfig() {
        try (InputStream input = DiscordChatBot.class.getClassLoader().getResourceAsStream("setup.yml")) {
            assert input != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("token:")) {
                        botToken = line.split(":", 2)[1].trim().replaceAll("\"", "");
                    } else if (line.startsWith("channel:")) {
                        channelId = line.split(":", 2)[1].trim().replaceAll("\"", "");
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to load the bot configuration from setup.yml", e);
        }
    }

    private static void sendToDiscord(String playerName, String message) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String webhookUrl = String.format("https://discord.com/api/v9/channels/%s/messages", channelId);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(webhookUrl))
                    .header("Authorization", "Bot " + botToken)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(
                            String.format("{\"content\": \"**%s** >> %s\"}", playerName, message),
                            StandardCharsets.UTF_8
                    ))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                log.error("Failed to send message to Discord. Response code: {}. Response body: {}", response.statusCode(), response.body());
            }

        } catch (Exception e) {
            log.error("Error sending message to Discord", e);
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel().getId().equals(channelId) && !event.getAuthor().isBot()) {
            String displayName = event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName();
            String message = event.getMessage().getContentDisplay();

            MinecraftServer.getSchedulerManager().buildTask(() -> {
                Audiences.players().sendMessage(Component.text("")
                        .append(Component.text(displayName)
                                .color(TextColor.color(142, 78, 191)))
                        .append(Component.text(" >> "))
                        .color(TextColor.color(128, 99, 150))
                        .append(Component.text(message)
                                .color(TextColor.color(211, 178, 237))));
            }).schedule();
        }
    }
}