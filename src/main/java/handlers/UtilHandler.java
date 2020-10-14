package handlers;

import model.Game;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.RestAction;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UtilHandler {

    public static String[] emotes = {"0️⃣", "1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣", "6️⃣", "7️⃣", "8️⃣", "9️⃣"};

    static public void addReactionsBasedOnParticipants(Message message, List<Member> members) {
        for (int i = 0; i < members.size(); i++) {
            message.addReaction(emotes[i]).queue();
        }
    }

    static public void muteMembers(List<Member> members) {
        for (Member member : members) {
            member.mute(true).queue();
        }
    }

    static public VoiceChannel getUserChannel(Guild guild, User user) {
        for (VoiceChannel vc : guild.getVoiceChannels()) {
            for (Member member : vc.getMembers()) {
                if (member.getUser().getAsTag().equals(user.getAsTag())) {
                    return vc;
                }
            }
        }
        return null;
    }

    static public List<Member> getUsersInVoiceChannel(VoiceChannel voiceChannel) {
        return voiceChannel.getMembers();
    }

    static public boolean isHostInGame(Member member) {
        for (Game game : GameHandler.activeGames.values()) {
            for (Member mbr : game.getParticipants()) {
                if (mbr.getId().equals(member.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    static public String getEmoteByString(String str) {
        String emote = "";
        if (str.equals("RE:U+1f3a4")) {
            emote = "mic";
        } else if (str.equals("RE:U+274c")) {
            emote = "stop";
        } else if (str.equals("RE:U+30U+fe0fU+20e3")) {
            emote = "0";
        } else if (str.equals("RE:U+31U+fe0fU+20e3")) {
            emote = "1";
        } else if (str.equals("RE:U+32U+fe0fU+20e3")) {
            emote = "2";
        } else if (str.equals("RE:U+33U+fe0fU+20e3")) {
            emote = "3";
        } else if (str.equals("RE:U+34U+fe0fU+20e3")) {
            emote = "4";
        } else if (str.equals("RE:U+35U+fe0fU+20e3")) {
            emote = "5";
        } else if (str.equals("RE:U+36U+fe0fU+20e3")) {
            emote = "6";
        } else if (str.equals("RE:U+37U+fe0fU+20e3")) {
            emote = "7";
        } else if (str.equals("RE:U+38U+fe0fU+20e3")) {
            emote = "8";
        } else if (str.equals("RE:U+39U+fe0fU+20e3")) {
            emote = "9";
        }

        return emote;
    }
}
