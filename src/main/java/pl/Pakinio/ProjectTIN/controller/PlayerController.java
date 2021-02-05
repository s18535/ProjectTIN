package pl.Pakinio.ProjectTIN.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.Pakinio.ProjectTIN.exception.NotFoundIdException;
import pl.Pakinio.ProjectTIN.exception.PlayerAlreadyExistException;
import pl.Pakinio.ProjectTIN.dto.PlayerInTeamReadModel;
import pl.Pakinio.ProjectTIN.model.Player;
import pl.Pakinio.ProjectTIN.model.User;
import pl.Pakinio.ProjectTIN.repository.PlayerRepository;
import pl.Pakinio.ProjectTIN.service.PlayerService;
import pl.Pakinio.ProjectTIN.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
//@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerRepository playerRepository;
    private final UserService userService;

    public PlayerController(PlayerService playerService, PlayerRepository playerRepository,UserService userService) {
        this.playerService = playerService;
        this.playerRepository = playerRepository;
        this.userService=userService;
    }

    @GetMapping("/players")
    String showAllPlayers(Model model,Authentication authentication) {
        List<Player> readAll = playerService.readAll();
        List<Player> readAllForUser=playerService.showPLayerForUsers(authentication.getName());
        model.addAttribute("players", readAll);
        model.addAttribute("playersUser",readAllForUser);
        return "pages/player-list";
    }
    /*@GetMapping("/players")
    String showAllPlayersForUsers(Model model, Authentication authentication){
        List<Player> readAll = playerService.showPLayerForUsers(authentication.getName());
        model.addAttribute("players", readAll);
        return "pages/player-list";
    }*/

    @GetMapping("/newplayer")
    public String createPlayer(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("default", " ");
        return "pages/player-form";
    }

    @PostMapping("/players")
    public String savePlayer(@Valid Player player, BindingResult bindingResult, Model model,Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "pages/player-form";
        } else {
            try {
                User user=userService.findUser(authentication.getName());
                player.setUser(user);
                playerService.createPLayer(player);
            } catch (PlayerAlreadyExistException | NotFoundIdException e) {
                bindingResult.rejectValue("email", "error.user", "Podany email istnieje już bazie. Podaj inny adres email");
                return "pages/player-form";
            }
            //playerService.save(player);
            return "redirect:/players";
        }
    }

    @GetMapping("/player")
    public String getPlayer(@RequestParam("id") int id, Model model) throws NotFoundIdException {
        Player player = playerService.findPlayer(id);
        model.addAttribute("player", player);
        List<PlayerInTeamReadModel> playerInTeam = playerService.showPlayerInTeam(id);
        model.addAttribute("playerInTeams", playerInTeam);
        return "pages/player-details";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/player/delete/{id}")
    public String deletePlayer(@PathVariable Integer id) {
        playerService.deletePlayer(id);
        return "redirect:/players";
    }

    @GetMapping("/player/edit/{id}")
    public String editPlayer(@PathVariable int id, Model model) throws NotFoundIdException {
        Player player = playerService.findPlayer(id);
        model.addAttribute("player", player);
        model.addAttribute("default", " ");
        return "pages/player-edit";
    }

    @PostMapping("/player/{id}")
    public String updatePlayer(@PathVariable int id, @Valid Player toUpdate, BindingResult bindingResult) throws NotFoundIdException {
        //playerRepository.findById(id).ifPresent(player -> {
        if (bindingResult.hasErrors()) {
            toUpdate.setIdPlayer(id);
            return "pages/player-edit";

        } else {
            playerService.findPlayerUpdate(id, toUpdate);
            //});
            return "redirect:/player?id=" + id;
        }
    }
}
