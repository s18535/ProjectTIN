package pl.Pakinio.ProjectTIN.service;

import org.springframework.stereotype.Service;
import pl.Pakinio.ProjectTIN.exception.NotFoundIdException;
import pl.Pakinio.ProjectTIN.exception.TeamAlreadyExistException;
import pl.Pakinio.ProjectTIN.dto.TeamInPlayerReadModel;
import pl.Pakinio.ProjectTIN.model.Player;
import pl.Pakinio.ProjectTIN.model.Team;
import pl.Pakinio.ProjectTIN.repository.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private TeamRepository teamRepository;

    TeamService(final TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> readAll() {
        return teamRepository.findAll();
    }

    public void createTeam(Team team) throws TeamAlreadyExistException {
        if (teamRepository.existsByTeamName(team.getTeamName())){
            throw new TeamAlreadyExistException("Team already exists for this teamName");
        }
        teamRepository.save(team);
    }

    public void deleteTeam(int id) {
        teamRepository.deleteById(id);
    }

    public Team findTeam(int id) throws NotFoundIdException {
        return teamRepository.findById(id).
                orElseThrow(() -> new NotFoundIdException("not found"));
    }

    public void findTeamUpdate(int id, Team toUpdate) {
        teamRepository.findById(id).ifPresent(player -> {
            player.updateForm(toUpdate);
            teamRepository.save(player);
        });
    }

    public Team save(Team toSave) {
        return teamRepository.save(toSave);
    }

    public List<TeamInPlayerReadModel> showTeamInPlayer(int id) throws NotFoundIdException {
        Team team = teamRepository.findById(id).orElseThrow(() -> new NotFoundIdException("not found"));
        List<TeamInPlayerReadModel> result = team.getPlayerTeams().stream().map(element -> {
            TeamInPlayerReadModel readModel = new TeamInPlayerReadModel();
            readModel.setDateFrom(element.getDateFrom());
            readModel.setDateTo(element.getDateTo());
            readModel.setFirstName(element.getPlayer().getFirstName());
            readModel.setLastName(element.getPlayer().getLastName());
            return readModel;
        }).collect(Collectors.toList());
        return result;
    }
    public List<Team> showPLayerForUsers(String name){
        return teamRepository.findByUserUsername(name);
    }

}
