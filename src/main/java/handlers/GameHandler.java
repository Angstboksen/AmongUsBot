package handlers;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GameHandler {
    static public void startGame(User user, MessageChannel channel, Guild guild) {

        VoiceChannel vc = UtilHandler.getUserChannel(guild, user);

        UUID gameId = UUID.randomUUID();

        String participantText = "";
        List<Member> participants = UtilHandler.getUsersInVoiceChannel(vc);
        for(Member member : participants) {
            participantText += member.getEffectiveName() + " (" + member.getUser().getId() + ")";
            if(user.getId().equalsIgnoreCase(member.getId())) participantText += " - Host";
            participantText += "\n";
        }

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Among Us Game");
        eb.setColor(Color.red);
        eb.setDescription("Deltagere: \n" +
                participantText +
                "\n" +
                "ID: " + gameId.toString());
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        Date result = new Date(System.currentTimeMillis());

        eb.setFooter("Runde startet: " + simple.format(result));

        channel.sendMessage(eb.build()).queue(message -> {
            message.addReaction("\uD83C\uDFA4").queue();
            UtilHandler.addReactionsBasedOnParticipants(message, participants);
            message.addReaction("❌").queue();
        });
    }
}
