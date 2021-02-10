package pl.qubiak.SubmitIdeas.Controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.qubiak.SubmitIdeas.Model.Ideas.Ideas;
import pl.qubiak.SubmitIdeas.Model.Token.Token;
import pl.qubiak.SubmitIdeas.Model.Users.AppUser;
import pl.qubiak.SubmitIdeas.Repo.AppUserRepo;
import pl.qubiak.SubmitIdeas.Repo.IdeasRepo;
import pl.qubiak.SubmitIdeas.Repo.TokenRepo;
import pl.qubiak.SubmitIdeas.Service.UserService;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class AppController {

    private UserService userService;
    private AppUserRepo appUserRepo;
    private IdeasRepo ideasRepo;
    private TokenRepo tokenRepo;

    public AppController(UserService userService, AppUserRepo appUserRepo, IdeasRepo ideasRepo, TokenRepo tokenRepo) {
        this.userService = userService;
        this.appUserRepo = appUserRepo;
        this.ideasRepo = ideasRepo;
        this.tokenRepo = tokenRepo;
    }

    //ADMIN
    @GetMapping("/deleteUser")
    public String deleteUser(
            @RequestParam Long id) {
        appUserRepo.deleteById(id);
        return "delete user with id: " + id;
    }

    //ADMIN
    @Transactional
    @GetMapping("/changeRoleToMod")
    public void changeUserRole(@RequestParam Long id) {
        try {
            Optional<AppUser> user1 = appUserRepo.findById(id);
            user1.orElseThrow().setRole("ROLE_MOD");
        } catch (NullPointerException e) {
            System.out.println("No ID");
            e.printStackTrace();
        }
    }

    //ADMIN
    @GetMapping("/all")
    public List<AppUser> allUsers() {
        return appUserRepo.findAll();
    }

    //MOD
    @GetMapping("/getUnacceptedIdeas")
    public List<pl.qubiak.SubmitIdeas.Model.Ideas.Ideas> getUnacceptedIdeas() {
        return ideasRepo.findByAcceptedFalse();
    }

    //MOD
    @GetMapping("/deleteIdeaById")
    public String deleteIdea(
            @RequestParam Long id) {
        ideasRepo.deleteById(id);
        return "delete idea with id: " + id;
    }

    //MOD
    @Transactional
    @GetMapping("/acceptedIdeas")
    public String acceptedIdeas(
            @RequestParam Long id) {
        ideasRepo.findById(id).get().setAccepted(true);
        return "Accepted ideas with id: " + id;
    }

    @GetMapping(value = "/hello")
    public ModelAndView hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities", authorities);
        model.addAttribute("details", details);
        return new ModelAndView("hello");
    }

    @GetMapping(value = "/sing-up")
    public ModelAndView singup(Model model) {
        model.addAttribute("user", new AppUser());
        return new ModelAndView("singUp");
    }

    @GetMapping(value = "/register")
    public ModelAndView register(AppUser appUser) {
        userService.addUser(appUser);
        return new ModelAndView("singUp");
    }

    @GetMapping("/token")
    public String singup(@RequestParam String value) {
        Token byValue = tokenRepo.findByValue(value);
        AppUser appUser = byValue.getAppUser();
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
        return "hello";
    }

    //ALL
    @GetMapping("/getAcceptedIdeas")
    public List<Ideas> getAcceptedIdeas() {
        return ideasRepo.findByAcceptedTrue();

    }

    //ALL
    @GetMapping("/addIdeas")
    public String addIdeas(Principal principal,
            @RequestParam String idea) {

        pl.qubiak.SubmitIdeas.Model.Ideas.Ideas newIdea = new pl.qubiak.SubmitIdeas.Model.Ideas.Ideas();
        newIdea.setIdea(idea);
        newIdea.setAuthor(principal.getName());
        newIdea.setAccepted(false);
        ideasRepo.save(newIdea);
        return "Przesłany pomysł to: '" + idea + "' Autor: " + principal.getName();
    }
}
