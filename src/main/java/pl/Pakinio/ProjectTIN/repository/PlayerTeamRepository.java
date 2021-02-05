package pl.Pakinio.ProjectTIN.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.Pakinio.ProjectTIN.model.Player;
import pl.Pakinio.ProjectTIN.model.PlayerTeam;
import pl.Pakinio.ProjectTIN.model.PlayerTeamId;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerTeamRepository extends JpaRepository<PlayerTeam, PlayerTeamId> {

    List<PlayerTeam> findAll();

    PlayerTeam save(PlayerTeam playerTeam);

    void deleteById(PlayerTeamId id);

    Optional<PlayerTeam> findById(PlayerTeamId id);

    boolean existsById(PlayerTeamId id);

    List<PlayerTeam> findByPlayerIdPlayer(int id);

    List<PlayerTeam> findByTeamIdTeam(int id);

    Optional<PlayerTeam> findByPlayerIdPlayerAndTeamIdTeam(int idPlayer,int idTeam);

    void deleteByPlayerIdPlayerAndTeamIdTeam(int idPlayer,int idTeam);

    PlayerTeam save(PlayerTeamId id);

    boolean existsByPlayerIdPlayerAndTeamIdTeam(int idPlayer,int idTeam);

    List<PlayerTeam> findByUserUsername(String name);
}
