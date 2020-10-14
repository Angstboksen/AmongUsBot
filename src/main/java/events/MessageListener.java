package events;

import handlers.GameHandler;
import handlers.UtilHandler;
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

    String prefix = "!!";
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String message = event.getMessage().getContentRaw();
        MessageChannel channel = event.getChannel();
        User user = event.getAuthor();
        Member member = event.getMember();

        if(message.startsWith(prefix)) {
            if(message.equalsIgnoreCase(prefix + "startgame")) {
                if(member.getVoiceState().inVoiceChannel()) {
                    GameHandler.startGame(user, channel, event.getGuild());
                } else {
                    channel.sendMessage("Du må være i voicechat for å starte en runde.").queue();
                }
            } else if(message.equalsIgnoreCase(prefix + "help")) {
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Bitch need help?");
                eb.setDescription("**How I use?** \n" +
                        "1. Skriv \"" + prefix + "startgame\" \n" +
                        "2. Personen som skrev kommandoen styrer runden \n" +
                        " ved å mute under runden og huke av spillere som har død. \n" +
                        "3. Når runden er over, trykk på det røde krysset. \n\n" +

                        "**Kommandoer** \n\n" +
                        "!!help \n Viser denne listen over alle kommandoer \n\n" +
                        "!!startgame \n Starter en runde med alle som er i voicechat, som kun host kan styre");
                channel.sendMessage(eb.build()).queue();
            }
        }
    }
}

