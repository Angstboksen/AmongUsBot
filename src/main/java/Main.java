import events.MessageListener;
import events.ReactionListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {
        JDABuilder jdaBuilder = JDABuilder.createDefault("NzYzNzkzMTIwMjMxMTYxODU3.X383xg.XB1lUrR0St_hEKC2EfOQnzR3-Us");
        jdaBuilder.setActivity(Activity.playing("Among Us"));
        jdaBuilder.addEventListeners(new MessageListener());
        jdaBuilder.addEventListeners(new ReactionListener());
        JDA jda = jdaBuilder.build();
    }
}
