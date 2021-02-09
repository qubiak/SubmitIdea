package pl.qubiak.SubmitIdeas.Controller;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.This;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @DeleteMapping("/deleteUser")
    public String deleteUser(
            @RequestParam Long id) {
        appUserRepo.deleteById(id);
        return "delete user with id: " + id;
    }

    //ADMIN
    @Transactional
    @PutMapping("/changeRoleToMod")
    public void changeUserRole(@RequestParam Long id) {
        try {

            Optional<AppUser> user1 = appUserRepo.findById(id);
            user1.orElseThrow().setRole("ROLE_MOD");
            //appUserRepo.save(user1);
            //appUserRepo.update(id);
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
    @DeleteMapping("/deleteIdeaById")
    public String deleteIdea(
            @RequestParam Long id) {
        ideasRepo.deleteById(id);
        return "delete idea with id: " + id;
    }

    //MOD
    @PutMapping("/acceptedIdeas")
    public String acceptedIdeas(
            @RequestParam Long id) {
        ideasRepo.findById(id).get().setAccepted(true); // ??
        return "Accepted ideas with id: " + id;
    }
    @GetMapping("/start")
    public String hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities", authorities);
        model.addAttribute("details", details);
        return "start";
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


    //ALL
    @GetMapping("/getAcceptedIdeas")
    public List<Ideas> getAcceptedIdeas() {
        return ideasRepo.findByAcceptedTrue();

    }

    //ALL
    @PostMapping("/addIdeas")
    public void addIdeas(
            @RequestParam String idea,
            @RequestParam String author) { //dodać zalogowanego autora

        pl.qubiak.SubmitIdeas.Model.Ideas.Ideas newIdea = new pl.qubiak.SubmitIdeas.Model.Ideas.Ideas();
        newIdea.setIdea(idea);
        newIdea.setAuthor(author);
        newIdea.setAccepted(false);
        ideasRepo.save(newIdea);
    }
}
