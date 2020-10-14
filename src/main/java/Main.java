import events.MessageListener;
import events.ReactionListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;

import javax.security.auth.login.LoginException;

public class Main {
    public static JDA jda;

    public static void main(String[] args) throws LoginException {
        JDABuilder jdaBuilder = JDABuilder.createDefault("NzYzNzkzMTIwMjMxMTYxODU3.X383xg.16bEvKL3r2B21RhmrtiTtkx7e7g");
        jdaBuilder.setActivity(Activity.playing("Among Us (!!help)"));
        jdaBuilder.addEventListeners(new MessageListener());
        jdaBuilder.addEventListeners(new ReactionListener());
        jda = jdaBuilder.build();

        Guild guild = jda.getGuildById(737497504722976800L);

    }
}
