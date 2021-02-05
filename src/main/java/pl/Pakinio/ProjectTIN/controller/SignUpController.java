package pl.Pakinio.ProjectTIN.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.Pakinio.ProjectTIN.model.User;
import pl.Pakinio.ProjectTIN.service.SignUpService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class SignUpController {
    private SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping("/sign_up")
    public ModelAndView signUp(ModelAndView mav){
        mav.setViewName("pages/sign_up");
        mav.addObject("user",new User());
        return mav;
    }

    @PostMapping("/sign_up")
    public ModelAndView signUpPost(ModelAndView mav,@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            mav.setViewName("pages/sign_up");
            return mav;
        }
        //System.out.println("email: "+email);
        mav.setViewName("redirect:/login");
        //User user = new User(username,password);
        //user.setEmail(email);

        if (user.getPassword().equals(user.getCheckPassword())){
            user.setEnabled(true);
            signUpService.signUpUser(user);
        }else {
            mav.setViewName("pages/sign_up");
            return mav;
        }

        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView mav){
        mav.setViewName("pages/login");
        return mav;
    }
   /* @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }*/
}
