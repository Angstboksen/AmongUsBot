package handlers;

import net.dv8tion.jda.api.entities.*;

import java.util.List;

public class UtilHandler {

    static public void addReactionsBasedOnParticipants(Message message, List<Member> members) {
        String[] emojis = {"0️⃣", "1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣", "6️⃣", "7️⃣", "8️⃣", "9️⃣"};
        for(int i = 0; i < members.size(); i++) {
            message.addReaction(emojis[i]).queue();
        }
    }

    static public void muteMembers(List<Member> members) {
        for(Member member : members) {
            member.mute(true).queue();
        }
    }

    static public VoiceChannel getUserChannel(Guild guild, User user) {
        for(VoiceChannel vc : guild.getVoiceChannels()) {
            for(Member member : vc.getMembers()) {
                if(member.getUser().getAsTag().equals(user.getAsTag())) {
                    return vc;
                }
            }
        }
        return null;
    }

    static public List<Member> getUsersInVoiceChannel(VoiceChannel voiceChannel) {
        return voiceChannel.getMembers();
    }
}
