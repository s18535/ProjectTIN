package pl.Pakinio.ProjectTIN.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PlayerTeamId implements Serializable {

    @Column(name = "id_player")
    private int idPlayer;

    @Column(name = "id_team")
    private int idTeam;

    public PlayerTeamId() {
    }

    public PlayerTeamId(int idPlayer, int idTeam) {
        this.idPlayer =idPlayer;
        this.idTeam =idTeam;
    }
}
