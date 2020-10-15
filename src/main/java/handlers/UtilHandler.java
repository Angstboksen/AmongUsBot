package handlers;

import model.Game;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;

import java.util.List;

public class UtilHandler {

    static JDA jda;

    public static String[] emotes = {"U+0030 U+FE0F U+20E3", "U+0031 U+FE0F U+20E3", "U+0032 U+FE0F U+20E3", "U+0033 U+FE0F U+20E3", "U+0033 U+FE0F U+20E3",
            "U+0034 U+FE0F U+20E3", "U+0035 U+FE0F U+20E3", "U+0036 U+FE0F U+20E3", "U+0037 U+FE0F U+20E3", "U+0038 U+FE0F U+20E3", "U+0039 U+FE0F U+20E3",};

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

    static public String getEmoteByString(String str, TextChannel channel) {
        String emote = "";
        if (str.equals("RE:U+1f3a4")) {
            emote = "mic";
        } else if (str.equals("RE:U+274c")) {
            emote = "stop";
        } else if (str.startsWith("RE:U+3")){
            emote = String.valueOf(str.charAt(6));
        }

        return emote;
    }
}
