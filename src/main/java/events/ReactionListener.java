package events;

import handlers.GameHandler;
import handlers.UtilHandler;
import model.Game;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class ReactionListener extends ListenerAdapter {

    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (event.getUser().isBot()) return;

        TextChannel channel = event.getTextChannel();
        Guild guild = event.getGuild();
        User user = event.getUser();
        String messageId = event.getMessageId();

        VoiceChannel vc = UtilHandler.getUserChannel(guild, user);
        List<Member> membersInHostVC = UtilHandler.getUsersInVoiceChannel(vc);

        Game game = GameHandler.getGameByMember(event.getMember());

        if(game != null) {
            if (event.getMember().equals(game.getHost())) {
                String reactedTo = event.getReactionEmote().toString();
                String emote = UtilHandler.getEmoteByString(reactedTo, channel);
                if (emote.equals("mic")) {
                    if (!game.isMuted()) {
                        for (Member member : game.getParticipants()) {
                            member.mute(true).queue();
                        }
                        game.setMuted(true);
                    }
                } else if (emote.equals("stop")) {
                    game.setEnded(true);
                    GameHandler.editGameEmbed(game, channel, messageId);
                    for (Member member : game.getParticipants()) {
                        member.mute(false).queue();
                    }
                    GameHandler.activeGames.remove(game.getId());
                } else {
                    GameHandler.killMember(game,game.getParticipants().get(Integer.valueOf(emote)), channel, messageId);
                }
            }
        }
    }

    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
        if(event.getUser().isBot()) return;

        TextChannel channel = event.getTextChannel();
        Guild guild = event.getGuild();
        User user = event.getUser();
        String messageId = event.getMessageId();

        VoiceChannel vc = UtilHandler.getUserChannel(guild, user);
        List<Member> membersInHostVC = UtilHandler.getUsersInVoiceChannel(vc);

        Game game = GameHandler.getGameByMember(event.getMember());

        if(game != null) {
            if (event.getMember().equals(game.getHost())) {
                String reactedTo = event.getReactionEmote().toString();
                String emote = UtilHandler.getEmoteByString(reactedTo,channel);
                if (emote.equals("mic")) {
                    if (game.isMuted()) {
                        for (Member member : game.getParticipants()) {
                            if (!GameHandler.memberIsDead(game, member)) {
                                member.mute(false).queue();
                            }
                        }
                    }
                    game.setMuted(false);
                } else {
                    GameHandler.reviveMember(game,game.getParticipants().get(Integer.valueOf(emote)), channel, messageId);
                }
            }
        }
    }
}
