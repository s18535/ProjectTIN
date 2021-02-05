package pl.Pakinio.ProjectTIN.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPlayer;

    @Column(length = 30)
    //@NotBlank(message = "pole wymagane")
    @NotBlank(message = "{error.required}")
    //@Size(min = 2,max = 30,message = "Pole powinno zawierać od 2 do 30 znaków")
    @Size(min = 2,max = 30,message = "{error.size.name}")
    private String firstName;

    @Column(length = 30)
    //@NotBlank(message = "pole wymagane")
    @NotBlank(message = "{error.required}")
    //@Size(min = 2,max = 30,message = "Pole powinno zawierać od 2 do 30 znaków")
    @Size(min = 2,max = 30,message = "{error.size.name}")
    private String lastName;

    @Column(length = 40)
    //@NotBlank(message = "pole wymagane")
    @NotBlank(message = "{error.required}")
    //@Size(min = 5,max = 40,message = "Pole powinno zawierać od 5 do 40 znaków")
    @Size(min = 5,max = 40,message = "{error.size.email}")
    @Email(message = "{error.size.email.scructure}")
    private String email;

    @Column(length = 9)
    //@NotBlank(message = "pole wymagane")
    @NotEmpty(message = "{error.required}")
    //@Size(min = 9,max = 9,message = "Pole powinno zawierać 9 znaków")
    @Size(min = 9,max = 9,message = "{error.size.phone}")
    private String phoneNumber;

    //@NotBlank(message = "pole wymagane")
    @NotNull(message = "{error.required}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PlayerTeam> playerTeams=new ArrayList<>();

    /*@OneToOne(mappedBy = "player")
    private User user;*/
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Player() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public void updateForm(final Player source){
        firstName =source.firstName;
        lastName =source.lastName;
        email =source.email;
        phoneNumber =source.phoneNumber;
        dateOfBirth =source.dateOfBirth;
    }

    @Override
    public String toString() {
        return "Player{" +
                "IdPlayer=" + idPlayer +
                ", FirstName='" + firstName + '\'' +
                ", LastName='" + lastName + '\'' +
                ", Email='" + email + '\'' +
                ", PhoneNumber='" + phoneNumber + '\'' +
                ", DateOfBirth=" + dateOfBirth +
                ", playerTeams=" + playerTeams +
                '}';
    }
}
