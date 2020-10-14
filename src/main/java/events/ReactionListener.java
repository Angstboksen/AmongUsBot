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
                if (UtilHandler.getEmoteByString(reactedTo).equals("mic")) {
                    if (!game.isMuted()) {
                        for (Member member : game.getParticipants()) {
                            member.mute(true).queue();
                        }
                        game.setMuted(true);
                    }
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("stop")) {
                    game.setEnded(true);
                    GameHandler.editGameEmbed(game, channel, messageId);
                    for (Member member : game.getParticipants()) {
                        member.mute(false).queue();
                    }
                    GameHandler.activeGames.remove(game.getId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("0")) {
                    GameHandler.killMember(game, game.getParticipants().get(0), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("1")) {
                    GameHandler.killMember(game, game.getParticipants().get(1), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("2")) {
                    GameHandler.killMember(game, game.getParticipants().get(2), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("3")) {
                    GameHandler.killMember(game, game.getParticipants().get(3), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("4")) {
                    GameHandler.killMember(game, game.getParticipants().get(4), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("5")) {
                    GameHandler.killMember(game, game.getParticipants().get(5), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("6")) {
                    GameHandler.killMember(game, game.getParticipants().get(6), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("7")) {
                    GameHandler.killMember(game, game.getParticipants().get(7), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("8")) {
                    GameHandler.killMember(game, game.getParticipants().get(8), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("9")) {
                    GameHandler.killMember(game, game.getParticipants().get(9), event.getTextChannel(), event.getMessageId());
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
                if (UtilHandler.getEmoteByString(reactedTo).equals("mic")) {
                    if (game.isMuted()) {
                        for (Member member : game.getParticipants()) {
                            if (!GameHandler.memberIsDead(game, member)) {
                                member.mute(false).queue();
                            }
                        }
                    }
                    game.setMuted(false);
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("0")) {
                    GameHandler.reviveMember(game, game.getParticipants().get(0), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("1")) {
                    GameHandler.reviveMember(game, game.getParticipants().get(1), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("2")) {
                    GameHandler.reviveMember(game, game.getParticipants().get(2), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("3")) {
                    GameHandler.reviveMember(game, game.getParticipants().get(3), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("4")) {
                    GameHandler.reviveMember(game, game.getParticipants().get(4), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("5")) {
                    GameHandler.reviveMember(game, game.getParticipants().get(5), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("6")) {
                    GameHandler.reviveMember(game, game.getParticipants().get(6), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("7")) {
                    GameHandler.reviveMember(game, game.getParticipants().get(7), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("8")) {
                    GameHandler.reviveMember(game, game.getParticipants().get(8), event.getTextChannel(), event.getMessageId());
                } else if (UtilHandler.getEmoteByString(reactedTo).equals("9")) {
                    GameHandler.reviveMember(game, game.getParticipants().get(9), event.getTextChannel(), event.getMessageId());
                }
            }
        }
    }
}
