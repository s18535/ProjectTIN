package pl.Pakinio.ProjectTIN.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.Pakinio.ProjectTIN.exception.NotFoundIdException;
import pl.Pakinio.ProjectTIN.dto.PlayerTeamRead;
import pl.Pakinio.ProjectTIN.exception.PlayerAlreadyExistException;
import pl.Pakinio.ProjectTIN.exception.PlayerTeamAlreadyExistException;
import pl.Pakinio.ProjectTIN.model.*;
import pl.Pakinio.ProjectTIN.service.PlayerService;
import pl.Pakinio.ProjectTIN.service.PlayerTeamService;
import pl.Pakinio.ProjectTIN.service.TeamService;
import pl.Pakinio.ProjectTIN.service.UserService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PlayerTeamController {

    private final PlayerTeamService playerTeamService;
    private final PlayerService playerService;
    private final TeamService teamService;
    private final UserService userService;

    public PlayerTeamController(PlayerTeamService playerTeamService, PlayerService playerService, TeamService teamService,UserService userService) {
        this.playerTeamService = playerTeamService;
        this.playerService = playerService;
        this.teamService = teamService;
        this.userService=userService;
    }

    @GetMapping("/assigntos")
    String showAllAssignTo(Model model,Authentication authentication) throws NotFoundIdException {
        List<PlayerTeamRead> readAll = playerTeamService.readAssigningto();
        List<PlayerTeamRead> readAllForUser=playerTeamService.readAssigningtoForUser(authentication.getName());
        model.addAttribute("assigntos", readAll);
        model.addAttribute("assigntosUser", readAllForUser);
        return "pages/assigningto-list";
    }

    @GetMapping("/newassignto")
    public String createAssignTo(Model model,Authentication authentication) {
        model.addAttribute("assignto", new PlayerTeam());
        model.addAttribute("players", playerTeamService.readAllNamePlayer());
        model.addAttribute("playersUser",playerTeamService.readAllNamePlayerForUser(authentication.getName()));
        model.addAttribute("teams", playerTeamService.readAllNameTeam());
        model.addAttribute("teamsUser",playerTeamService.readAllNameTeamforUser(authentication.getName()));
        model.addAttribute("default"," ");
        return "pages/assigningto-form";
    }

    @PostMapping("/assigntos")
    public String saveAssignTo(@Valid @ModelAttribute("assignto") PlayerTeam playerTeam, BindingResult bindingResult, Model model, Authentication authentication) throws PlayerTeamAlreadyExistException {
        if (bindingResult.hasErrors()){
            model.addAttribute("assignto", playerTeam);
            model.addAttribute("players", playerTeamService.readAllNamePlayer());
            model.addAttribute("playersUser",playerTeamService.readAllNamePlayerForUser(authentication.getName()));
            model.addAttribute("teams", playerTeamService.readAllNameTeam());
            model.addAttribute("teamsUser",playerTeamService.readAllNameTeamforUser(authentication.getName()));
            return "pages/assigningto-form";
        }else {
            try {
                model.addAttribute("assignto", playerTeam);
                model.addAttribute("players", playerTeamService.readAllNamePlayer());
                model.addAttribute("playersUser",playerTeamService.readAllNamePlayerForUser(authentication.getName()));
                model.addAttribute("teams", playerTeamService.readAllNameTeam());
                model.addAttribute("teamsUser",playerTeamService.readAllNameTeamforUser(authentication.getName()));
                User user=userService.findUser(authentication.getName());
                playerTeam.setUser(user);
                playerTeamService.createAssignTo(playerTeam);
            } catch (PlayerTeamAlreadyExistException | NotFoundIdException e) {
                model.addAttribute("message","Nie można przypisać tego gracza do tej samej drużyny. Wybierz innego gracza lub drużyne");
                return "pages/assigningto-form";
            }
            return "redirect:/assigntos";
        }
    }

    @GetMapping("/assignto/{idPlayer}/{idTeam}")
    public String getAssignTo(@PathVariable int idPlayer, @PathVariable int idTeam, Model model) throws NotFoundIdException {
        PlayerTeam playerTeam=playerTeamService.findPlayerTeamWithIdPlayerAndIdTeam(idPlayer,idTeam);
        model.addAttribute("assignto",playerTeam);
       /* List<PlayerInTeamReadModel> playerInTeam=playerService.showPlayerInTeam(id);
        model.addAttribute("playerInTeams",playerInTeam);*/
        return "pages/assigningto-details";
    }

    @RequestMapping(value = "/assignto/delete/{idPlayer}/{idTeam}")
    public String deleteAssignTo(@PathVariable int idPlayer,@PathVariable int idTeam) throws NotFoundIdException {
        playerTeamService.deleteAssignTo(idPlayer,idTeam);
        return "redirect:/assigntos";
    }

    @GetMapping("/assignto/edit/{idPlayer}/{idTeam}")
    public String editAssigTo(@PathVariable Integer idPlayer,@PathVariable Integer idTeam, Model model) throws NotFoundIdException {
        PlayerTeam playerTeam=playerTeamService.findPlayerTeamWithIdPlayerAndIdTeam(idPlayer,idTeam);
        model.addAttribute("assignto",playerTeam);
        model.addAttribute("default"," ");
        return "pages/assigningto-edit";
    }
    @Transactional
    @PostMapping("/assignto/update/{idPlayer}/{idTeam}")
    public String updateAssignTo(@PathVariable Integer idPlayer,@PathVariable Integer idTeam,@Valid @ModelAttribute("assignto") PlayerTeam toUpdate,BindingResult bindingResult,RedirectAttributes redirAttrs,Model model) throws NotFoundIdException {
        if (bindingResult.hasErrors()){
            Player player=playerService.findPlayer(idPlayer);
            Team team=teamService.findTeam(idTeam);
            toUpdate.setPlayer(player);
            toUpdate.setTeam(team);
            model.addAttribute("assignto",toUpdate);
            toUpdate.setId(new PlayerTeamId(idPlayer,idTeam));
            List<String> error=new ArrayList<>();
            bindingResult.getAllErrors().forEach(objectError -> {
                error.add(objectError.getDefaultMessage());
            });
            return "pages/assigningto-edit";
        }
    playerTeamService.findPlayerTeamUpdate(idPlayer,idTeam,toUpdate);
        return "redirect:/assignto/"+idPlayer+"/"+idTeam;
    }
}
