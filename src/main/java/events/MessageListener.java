package events;

import handlers.GameHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessageListener extends ListenerAdapter {

    String prefix = "!";
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String message = event.getMessage().getContentRaw();
        MessageChannel channel = event.getChannel();
        User user = event.getAuthor();

        if(message.startsWith(prefix)) {
            if(message.equalsIgnoreCase(prefix + "startgame")) {
                GameHandler.startGame(user, channel, event.getGuild());
            }
        }
    }

    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if(event.getUser().isBot()) return;
    }
}

