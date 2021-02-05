package pl.Pakinio.ProjectTIN.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.Pakinio.ProjectTIN.exception.NotFoundIdException;
import pl.Pakinio.ProjectTIN.exception.TeamAlreadyExistException;
import pl.Pakinio.ProjectTIN.dto.TeamInPlayerReadModel;
import pl.Pakinio.ProjectTIN.model.Player;
import pl.Pakinio.ProjectTIN.model.Team;
import pl.Pakinio.ProjectTIN.model.User;
import pl.Pakinio.ProjectTIN.service.TeamService;
import pl.Pakinio.ProjectTIN.service.UserService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
public class TeamController {

    private final TeamService teamService;
    private final UserService userService;

    public TeamController(TeamService teamService,UserService userService){
        this.teamService=teamService;
        this.userService=userService;
    }

    @GetMapping("/teams")
    String showAllTeams(Model model,Authentication authentication) {
        List<Team> readAll = teamService.readAll();
        List<Team> readAllForUser=teamService.showPLayerForUsers(authentication.getName());
        model.addAttribute("teams", readAll);
        model.addAttribute("teamsUser",readAllForUser);
        return "pages/team-list";
    }

    @GetMapping("/newteam")
    public String createTeam(Model model) {
        model.addAttribute("team", new Team());
        model.addAttribute("default"," ");
        return "pages/team-form";

    }

    @PostMapping("/teams")
    public String saveTeam(@Valid Team team, BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()){
            return "pages/team-form";
        }else {
            try {
                User user=userService.findUser(authentication.getName());
                team.setUser(user);
                teamService.createTeam(team);
            } catch (TeamAlreadyExistException | NotFoundIdException e) {
                bindingResult.rejectValue("teamName", "error.user", "Podana druzyna istnieje juz w bazie");
                return "pages/team-form";
            }
            return "redirect:/teams";
        }
    }

    @GetMapping("/team")
    public String getTeam(@RequestParam("id") int id, Model model) throws NotFoundIdException {
        Team team=teamService.findTeam(id);
        model.addAttribute("team",team);
        List<TeamInPlayerReadModel> teamInPlayer=teamService.showTeamInPlayer(id);
        model.addAttribute("teamInPlayers",teamInPlayer);
        return "pages/team-details";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/team/delete/{id}")
    public String deleteTeam(@PathVariable Integer id){
        teamService.deleteTeam(id);
        return "redirect:/teams";
    }

    @GetMapping("/team/edit/{id}")
    public String editTeam(@PathVariable int id, Model model) throws NotFoundIdException {
        Team team=teamService.findTeam(id);
        model.addAttribute("team",team);
        model.addAttribute("default"," ");
        return "pages/team-edit";
    }

    @Transactional
    @PostMapping("/team/{id}")
    public String updateTeam(@PathVariable int id,@Valid Team toUpdate,BindingResult bindingResult) throws NotFoundIdException {
        if (bindingResult.hasErrors()){
            toUpdate.setIdTeam(id);
            return "pages/team-edit";
        }
        teamService.findTeamUpdate(id,toUpdate);
        return "redirect:/team?id="+id;
    }
}
