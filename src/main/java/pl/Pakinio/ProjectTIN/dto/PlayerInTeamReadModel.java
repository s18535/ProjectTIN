package pl.Pakinio.ProjectTIN.dto;

import java.time.LocalDate;

public class PlayerInTeamReadModel {

    private String teamName;
    private int numberOfShirt;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public PlayerInTeamReadModel(){};

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getNumberOfShirt() {
        return numberOfShirt;
    }

    public void setNumberOfShirt(int numberOfShirt) {
        this.numberOfShirt = numberOfShirt;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "PlayerInTeamReadModel{" +
                "teamName='" + teamName + '\'' +
                ", numberOfShirt=" + numberOfShirt +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
