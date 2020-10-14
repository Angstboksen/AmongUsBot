package model;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;
import java.util.UUID;

public class Game {

    private UUID id;

    private Member host;

    private List<Member> participants;

    private List<Member> dead;

    private EmbedBuilder eb;

    private boolean ended;

    public boolean hasEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }

    private boolean isMuted;

    public EmbedBuilder getEb() {
        return eb;
    }

    public void setEb(EmbedBuilder eb) {
        this.eb = eb;
    }

    public Game() {}
    public Game(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Member getHost() {
        return host;
    }

    public void setHost(Member host) {
        this.host = host;
    }

    public List<Member> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Member> participants) {
        this.participants = participants;
    }

    public List<Member> getDead() {
        return dead;
    }

    public void setDead(List<Member> dead) {
        this.dead = dead;
    }
}
