package pl.Pakinio.ProjectTIN.service;

import org.springframework.stereotype.Service;
import pl.Pakinio.ProjectTIN.exception.NotFoundIdException;
import pl.Pakinio.ProjectTIN.exception.PlayerAlreadyExistException;
import pl.Pakinio.ProjectTIN.dto.PlayerInTeamReadModel;
import pl.Pakinio.ProjectTIN.model.Player;
import pl.Pakinio.ProjectTIN.repository.PlayerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    PlayerService(final PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> readAll() {
        return playerRepository.findAll();
    }

    public void createPLayer(Player playerCreate) throws PlayerAlreadyExistException {
        if (playerRepository.existsByEmail(playerCreate.getEmail())){
            throw new PlayerAlreadyExistException("User already exists for this email");
        }
        playerRepository.save(playerCreate);
    }

    public void deletePlayer(int id) {
        playerRepository.deleteById(id);
        //System.out.println(id+" playerservice");
    }

    public Player findPlayer(int id) throws NotFoundIdException {
        return playerRepository.findById(id).
                orElseThrow(() -> new NotFoundIdException("not found"));
    }
    public void findPlayerUpdate(int id,Player toUpdate){
        playerRepository.findById(id).ifPresent(player->{player.updateForm(toUpdate);
            playerRepository.save(player);});
    }

    public Player save(Player toSave){
        return playerRepository.save(toSave);
    }

    public List<PlayerInTeamReadModel> showPlayerInTeam(int id) throws NotFoundIdException {
        Player player1 = playerRepository.findById(id).orElseThrow(() -> new NotFoundIdException("not found"));

        List<PlayerInTeamReadModel> result = player1.getPlayerTeams().stream().map(element -> {
            PlayerInTeamReadModel readModel = new PlayerInTeamReadModel();
            readModel.setDateFrom(element.getDateFrom());
            readModel.setDateTo(element.getDateTo());
            readModel.setNumberOfShirt(element.getNumOfShirt());
            readModel.setTeamName(element.getTeam().getTeamName());
            return readModel;
        }).collect(Collectors.toList());
        return result;
    }

    public List<Player> showPLayerForUsers(String name){
        return playerRepository.findByUserUsername(name);
    }
}
