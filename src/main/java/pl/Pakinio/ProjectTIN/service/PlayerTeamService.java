package pl.Pakinio.ProjectTIN.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.Pakinio.ProjectTIN.exception.NotFoundIdException;
import pl.Pakinio.ProjectTIN.dto.PlayerDTO;
import pl.Pakinio.ProjectTIN.dto.TeamDTO;
import pl.Pakinio.ProjectTIN.exception.PlayerTeamAlreadyExistException;
import pl.Pakinio.ProjectTIN.model.Player;
import pl.Pakinio.ProjectTIN.model.PlayerTeam;
import pl.Pakinio.ProjectTIN.model.PlayerTeamId;
import pl.Pakinio.ProjectTIN.model.Team;
import pl.Pakinio.ProjectTIN.dto.PlayerTeamRead;
import pl.Pakinio.ProjectTIN.repository.PlayerRepository;
import pl.Pakinio.ProjectTIN.repository.PlayerTeamRepository;
import pl.Pakinio.ProjectTIN.repository.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerTeamService {

    private PlayerTeamRepository playerTeamRepository;
    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;
    private PlayerService playerService;
    private TeamService teamService;

    public PlayerTeamService(final PlayerTeamRepository playerTeamRepository,final PlayerRepository playerRepository,final TeamRepository teamRepository,
                             final PlayerService playerService,TeamService teamService){
        this.playerTeamRepository=playerTeamRepository;
        this.playerRepository=playerRepository;
        this.teamRepository=teamRepository;
        this.playerService=playerService;
        this.teamService=teamService;
    }

    public List<PlayerTeam> readAll() {
        return playerTeamRepository.findAll();
    }

    public PlayerTeam findById(PlayerTeamId id) throws NotFoundIdException {
        return playerTeamRepository.findById(id).orElseThrow(() -> new NotFoundIdException("not found"));
    }

    public PlayerTeam save(PlayerTeam toSave){
        return playerTeamRepository.save(toSave);
    }

    /*public List<PlayerTeamRead> readAssigningto2() throws NotFoundIdException {
        List<PlayerTeam> list=playerTeamRepository.findAll();
        List<PlayerTeamRead> result=new ArrayList<>();

        for (int i = 0; i <list.size() ; i++) {
            String firstName;
            String lastName;
            String teamName;
            Player player= playerRepository.findById(list.get(i).getPlayer().getIdPlayer())
                    .orElseThrow(() -> new NotFoundIdException("not found"));
            firstName=player.getFirstName();
            lastName=player.getLastName();

            Team team= teamRepository.findById(list.get(i).getTeam().getIdTeam())
                    .orElseThrow(() -> new NotFoundIdException("not found"));
            teamName=team.getTeamName();

            result.add(new PlayerTeamRead(player,team));
        }
        return result;
    }*/

    public void createAssignTo(PlayerTeam playerTeam) throws PlayerTeamAlreadyExistException {
        if (playerTeamRepository.existsByPlayerIdPlayerAndTeamIdTeam(playerTeam.getPlayer().getIdPlayer(),playerTeam.getTeam().getIdTeam())){
            throw new PlayerTeamAlreadyExistException("Player in Team already exist");
        }else {
            Player player = playerTeam.getPlayer();
            //player.getPlayerTeams().add(playerTeam);
            Team team = playerTeam.getTeam();
            //team.getPlayerTeams().add(playerTeam);
            playerTeam.setId(new PlayerTeamId(player.getIdPlayer(), team.getIdTeam()));
            //playerService.findPlayerUpdate(player.getIdPlayer(), player);
            //teamService.findTeamUpdate(team.getIdTeam(), team);
            playerTeamRepository.save(playerTeam);
        }
    }

    public List<PlayerTeamRead> readAssigningto() throws NotFoundIdException {
        List<PlayerTeam> list=playerTeamRepository.findAll();

        return list.stream().map(element->{
            Player player=element.getPlayer();
            Team team=element.getTeam();
            return new PlayerTeamRead(player,team);
        }).collect(Collectors.toList());

    }

    public List<PlayerTeamRead> readAssigningtoForUser(String name) throws NotFoundIdException {
        List<PlayerTeam> list=playerTeamRepository.findByUserUsername(name);

        return list.stream().map(element->{
            Player player=element.getPlayer();
            Team team=element.getTeam();
            return new PlayerTeamRead(player,team);
        }).collect(Collectors.toList());

    }

    public List<PlayerDTO> readAllNamePlayer(){
        return playerRepository.findAll().stream().map(element->{
            return new PlayerDTO(element);
        }).collect(Collectors.toList());
    }
    public List<PlayerDTO> readAllNamePlayerForUser(String name){
        return playerRepository.findByUserUsername(name).stream().map(element->{
            return new PlayerDTO(element);
        }).collect(Collectors.toList());
    }

    public List<TeamDTO> readAllNameTeam(){
        return teamRepository.findAll().stream().map(element->{
            return new TeamDTO(element);
        }).collect(Collectors.toList());
    }
    public List<TeamDTO> readAllNameTeamforUser(String name){
        return teamRepository.findByUserUsername(name).stream().map(element->{
            return new TeamDTO(element);
        }).collect(Collectors.toList());
    }

    public PlayerTeam findPlayerTeamWithIdPlayerAndIdTeam(int idPlayer,int idTeam) throws NotFoundIdException {
        return playerTeamRepository.findByPlayerIdPlayerAndTeamIdTeam(idPlayer,idTeam)
                .orElseThrow(() -> new NotFoundIdException("not found"));
    }

    public void deleteAssignTo(int idPlayer,int idTeam) throws NotFoundIdException {
        PlayerTeam playerTeam=playerTeamRepository.findByPlayerIdPlayerAndTeamIdTeam(idPlayer,idTeam)
                .orElseThrow(() -> new NotFoundIdException("not found"));
        Player player=playerRepository.findById(idPlayer)
                .orElseThrow(() -> new NotFoundIdException("not found"));
        player.getPlayerTeams().remove(playerTeam);
        Team team=teamRepository.findById(idTeam)
                .orElseThrow(() -> new NotFoundIdException("not found"));
        team.getPlayerTeams().remove(playerTeam);
        PlayerTeamId id=playerTeam.getId();
        playerTeamRepository.deleteById(id);
    }

    public void findPlayerTeamUpdate(int idPlayer,int idTeam,PlayerTeam toUpdate) throws NotFoundIdException {

        PlayerTeam playerTeam=playerTeamRepository.findByPlayerIdPlayerAndTeamIdTeam(idPlayer,idTeam)
                .orElseThrow(() -> new NotFoundIdException("not found"));
        //System.out.println(playerTeam);
        playerTeam.updateForm(toUpdate);
    }
    /*public List<PlayerTeam> showPLayerForUsers(String name){
        return playerTeamRepository.findByUserUsername(name);
    }*/
}
