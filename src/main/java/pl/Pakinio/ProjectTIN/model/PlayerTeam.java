package pl.Pakinio.ProjectTIN.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "Player_Team")
public class PlayerTeam {
    @EmbeddedId
    private PlayerTeamId id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @MapsId("idplayer")
    @JoinColumn(name = "id_player")
    //@NotNull(message = "pole wymagane")
    @NotNull(message = "{error.required}")
    private Player player;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @MapsId("idteam")
    @JoinColumn(name = "id_team")
    //@NotNull(message = "pole wymagane")
    @NotNull(message = "{error.required}")
    private Team team;

    //@NotNull(message = "pole wymagane")
    @NotNull(message = "{error.required}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    //@NotNull(message = "pole wymagane")
    @NotNull(message = "{error.required}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    //@NotNull(message = "pole wymagane")
    @NotNull(message = "{error.required}")
    //@Min(value = 1,message = "minimalna wartosc to 1")
    //@Max(value = 100,message = "maksymalna wartosc to 100")
    @Min(value = 1,message = "{error.size.minShirt}")
    @Max(value = 100,message = "{error.size.maxShirt}")
    private int numOfShirt;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    public PlayerTeam() {
    }

    public PlayerTeam(Player player,Team team){
        this.id=new PlayerTeamId(player.getIdPlayer(),team.getIdTeam());
        this.player=player;
        this.team=team;
    }

    public void updateForm(final PlayerTeam source){
        dateFrom =source.dateFrom;
        dateTo =source.dateTo;
        numOfShirt =source.numOfShirt;
    }

    public PlayerTeamId getId() {
        return id;
    }

    public void setId(PlayerTeamId id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    public int getNumOfShirt() {
        return numOfShirt;
    }

    public void setNumOfShirt(int numOfShirt) {
        this.numOfShirt = numOfShirt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PlayerTeam{" +
                ", player=" + player.getIdPlayer() +
                ", team=" + team.getIdTeam() +
                ", DateFrom=" + dateFrom +
                ", DateTo=" + dateTo +
                ", NumOfShirt=" + numOfShirt +
                '}';
    }
}
