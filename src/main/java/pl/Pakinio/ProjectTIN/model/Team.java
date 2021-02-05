package pl.Pakinio.ProjectTIN.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTeam;

    @Column(length = 20)
    //@NotBlank(message = "pole wymagane")
    @NotBlank(message = "{error.required}")
    //@Size(min = 2, max = 20, message = "Pole powinno zawierać od 2 do 20 znaków")
    @Size(min = 2, max = 20, message = "{error.size.teamname}")
    private String teamName;

    @Column(length = 300)
    //@Size(max = 300, message = "Pole może zawierac max 300 znaków")
    @Size(max = 300, message = "{error.size.description}")
    private String description;

    //@NotNull(message = "pole wymagane")
    @NotNull(message = "{error.required}")
    //@Min(value = 16, message = "Wartosc nie moze byc mniejsza niz 16")
    //@Max(value = 100, message = "Wartość nie omze byc większa niż 100")
    @Min(value = 16, message = "{error.size.minAge}")
    @Max(value = 100, message = "{error.size.maxAge}")
    private int minAge;

    //@NotNull(message = "pole wymagane")
    @NotNull(message = "{error.required}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<PlayerTeam> playerTeams = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Team() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public List<PlayerTeam> getPlayerTeams() {
        return playerTeams;
    }

    public void setPlayerTeams(List<PlayerTeam> playerTeams) {
        this.playerTeams = playerTeams;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void updateForm(final Team source) {
        teamName = source.teamName;
        description = source.description;
        minAge = source.minAge;
        dateTo = source.dateTo;
    }

    @Override
    public String toString() {
        return "Team{" +
                "IdTeam=" + idTeam +
                ", TeamName='" + teamName + '\'' +
                ", Description='" + description + '\'' +
                ", MinAge=" + minAge +
                ", DateTo=" + dateTo +
                ", playerTeams=" + playerTeams +
                '}';
    }
}
