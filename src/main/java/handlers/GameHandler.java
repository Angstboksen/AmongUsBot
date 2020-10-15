package handlers;

import model.Game;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class GameHandler {

    static public HashMap<UUID, Game> activeGames = new HashMap<>();

    static public void startGame(User user, MessageChannel channel, Guild guild) {

        VoiceChannel vc = UtilHandler.getUserChannel(guild, user);
        List<Member> participants = UtilHandler.getUsersInVoiceChannel(vc);
        UUID gameId = UUID.randomUUID();
        Member host = guild.getMember(user);

        Game game = new Game();
        game.setHost(host);
        game.setId(gameId);
        game.setParticipants(participants);
        game.setMuted(false);
        game.setEnded(false);

        channel.sendMessage(createEmbed(game)).queue(message -> {
            message.addReaction("\uD83C\uDFA4").queue();
            UtilHandler.addReactionsBasedOnParticipants(message, participants);
            message.addReaction("\u274C").queue();

            activeGames.put(gameId, game);

        });
    }

    public static void editGameEmbed(Game game, TextChannel channel, String messageId) {
        channel.editMessageById(messageId, createEmbed(game)).queue();
    }

    public static MessageEmbed createEmbed(Game game) {

        String participants = "";
        for(Member member : game.getParticipants()) {
            if(memberIsDead(game, member)) participants += "(:skull:) ";
            participants += member.getEffectiveName() + " (" + member.getId() + ")";
            if(member.getId().equals(game.getHost().getId())) participants += " - Host";
            participants += "\n";
        }

        String ended = "";
        if(game.hasEnded()) {
            ended += "Runden er avsluttet." + "\n\n";
        }

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Among Us Game");
        eb.setColor(Color.red);
        eb.setDescription(ended +
                "**Deltagere (" + game.getParticipants().size() + "/10): **\n" +
                participants +
                "\n" +
                "**ID: **" + game.getId().toString());
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        Date result = new Date(System.currentTimeMillis());
        eb.setFooter("Runde startet: " + simple.format(result));

        return eb.build();
    }

    public static boolean memberIsDead(Game game, Member member) {
        if(game.getDead() != null) {
            if (game.getDead().contains(member)) {
                return true;
            }
        }
        return false;
    }

    static public Game getGameByMember(Member member) {
        for (Map.Entry<UUID, Game> entry : GameHandler.activeGames.entrySet()) {
            Game game = entry.getValue();
            for (Member mbr : game.getParticipants()) {
                if (mbr.getId().equals(member.getId())) {
                    return game;
                }
            }
        }
        return null;
    }

    static public void killMember(Game game, Member member, TextChannel channel, String messageId) {
        List<Member> dead = new ArrayList<>();
        if(game.getDead() != null) {
            for(Member mem : game.getDead()) {
                dead.add(mem);
            }
        }
        dead.add(member);
        game.setDead(dead);
        member.mute(true).queue();

        editGameEmbed(game, channel, messageId);
    }

    static public void reviveMember(Game game, Member member, TextChannel channel, String messageId) {
        if(memberIsDead(game, member)) {
            game.getDead().remove(member);
        }
        if(!game.isMuted()) {
            member.mute(false).queue();
        }

        editGameEmbed(game, channel, messageId);
    }
}
