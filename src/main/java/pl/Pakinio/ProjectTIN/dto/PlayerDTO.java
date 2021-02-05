package pl.Pakinio.ProjectTIN.dto;

import pl.Pakinio.ProjectTIN.model.Player;

public class PlayerDTO {
    private int idPlayer;
    private String firstName;
    private String lastName;

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
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

    public PlayerDTO(Player player){
        this.firstName=player.getFirstName();
        this.lastName=player.getLastName();
        this.idPlayer=player.getIdPlayer();
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "idPlayer=" + idPlayer +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
