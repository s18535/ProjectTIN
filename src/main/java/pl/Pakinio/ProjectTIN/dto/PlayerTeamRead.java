package pl.Pakinio.ProjectTIN.dto;

import pl.Pakinio.ProjectTIN.model.Player;
import pl.Pakinio.ProjectTIN.model.Team;

public class PlayerTeamRead {
    private String firstName;
    private String lastName;
    private String teamName;
    private int idPlayer;
    private int idTeam;

    public PlayerTeamRead(Player player, Team team){
        firstName=player.getFirstName();
        lastName=player.getLastName();
        teamName=team.getTeamName();
        idPlayer=player.getIdPlayer();
        idTeam=team.getIdTeam();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    @Override
    public String toString() {
        return "PlayerTeamRead{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
