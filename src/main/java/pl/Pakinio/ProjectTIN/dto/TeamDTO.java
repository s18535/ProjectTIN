package pl.Pakinio.ProjectTIN.dto;

import pl.Pakinio.ProjectTIN.model.Team;

public class TeamDTO {
    private int idTeam;
    private String teamName;

    public TeamDTO(Team team){
        this.idTeam=team.getIdTeam();
        this.teamName=team.getTeamName();
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "idTeam=" + idTeam +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
