package pl.qubiak.SubmitIdeas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.qubiak.SubmitIdeas.Model.Token.Token;
import pl.qubiak.SubmitIdeas.Model.Users.AppUser;
import pl.qubiak.SubmitIdeas.Repo.AppUserRepo;
import pl.qubiak.SubmitIdeas.Repo.Dao.Ideas;
import pl.qubiak.SubmitIdeas.Repo.Dao.User;
import pl.qubiak.SubmitIdeas.Repo.TokenRepo;
import pl.qubiak.SubmitIdeas.Service.UserService;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private AppUserRepo appUserRepo;
    private TokenRepo tokenRepo;
    @Autowired
    public User user;
    @Autowired
    public Ideas ideas;


    public UserController(UserService userService, AppUserRepo appUserRepo, TokenRepo tokenRepo) {
        this.userService = userService;
        this.appUserRepo = appUserRepo;
        this.tokenRepo = tokenRepo;

    }

    //All
    @GetMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities", authorities);
        model.addAttribute("details", details);
        return "hello";
    }

    //ALL
    @GetMapping("/sing-up")
    public String singup(Model model) {
        model.addAttribute("user", new AppUser());
        return "sing-up";
    }
    //ALL
    @PostMapping("/register")
    public String register(AppUser appUser) {
        userService.addUser(appUser);
        return "register";
    }

    @GetMapping("/token")
    public String singup(@RequestParam String value) {
        Token byValue = tokenRepo.findByValue(value);
        AppUser appUser = byValue.getAppUser();
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
        return "hello";
    }

    //ADMIN
    @RequestMapping("/changeRoleToMod")
    @ResponseBody
    public void changeUserRole(@RequestParam int id) {
        try {
            user.changeRoleToMod(id);
        } catch (NullPointerException e) {
            System.out.println("No ID");
            e.printStackTrace();
        }
    }

    //ADMIN
    @RequestMapping("/all")
    @ResponseBody
    public List<AppUser> allUsers() {
        return user.allUsers();
    }

    @RequestMapping("/addIdeas")
    @ResponseBody
    public void addIdeas(
            @RequestParam String idea,
            @RequestParam String author) { //dodać zalogowanego autora
        ideas.addIdeas(idea, author);
    }

    @RequestMapping("/getAcceptedIdeas")
    @ResponseBody
    public List<pl.qubiak.SubmitIdeas.Model.Ideas.Ideas> getAcceptedIdeas() {
        return ideas.getAcceptedIdeas();
    }

    @RequestMapping("/getUnacceptedIdeas")
    @ResponseBody
    public List<pl.qubiak.SubmitIdeas.Model.Ideas.Ideas> getUnacceptedIdeas() {
        return ideas.getUnacceptedIdeas();
    }

    @RequestMapping("/deleteIdeaById")
    @ResponseBody
    public void dwleteIdea(
            @RequestParam int id) {
        ideas.delateIdea(id);
    }
}

