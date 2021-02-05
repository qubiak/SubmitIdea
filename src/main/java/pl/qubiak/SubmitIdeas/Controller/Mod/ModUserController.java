package pl.qubiak.SubmitIdeas.Controller.Mod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.qubiak.SubmitIdeas.Repo.AppUserRepo;
import pl.qubiak.SubmitIdeas.Repo.Dao.Ideas;
import pl.qubiak.SubmitIdeas.Repo.Dao.User;
import pl.qubiak.SubmitIdeas.Repo.TokenRepo;
import pl.qubiak.SubmitIdeas.Service.UserService;

import java.util.List;

@RequestMapping("/mod")
@Controller
public class ModUserController {

    private UserService userService;
    private AppUserRepo appUserRepo;
    private TokenRepo tokenRepo;
    @Autowired
    public User user;
    @Autowired
    public Ideas ideas;


    public ModUserController(UserService userService, AppUserRepo appUserRepo, TokenRepo tokenRepo) {
        this.userService = userService;
        this.appUserRepo = appUserRepo;
        this.tokenRepo = tokenRepo;
    }


    //MOD
    @RequestMapping("/getUnacceptedIdeas")
    @ResponseBody
    public List<pl.qubiak.SubmitIdeas.Model.Ideas.Ideas> getUnacceptedIdeas() {
        return ideas.getUnacceptedIdeas();
    }

    //MOD
    @RequestMapping("/deleteIdeaById")
    @ResponseBody
    public String deleteIdea(
            @RequestParam int id) {
        ideas.delateIdea(id);
        return "delete idea with id: " + id;
    }

    //MOD
    @RequestMapping("/acceptedIdeas")
    @ResponseBody
    public String acceptedIdeas(
            @RequestParam int id) {
        ideas.acceptIdeas(id);
        return "Accepted ideas with id: " + id;
    }
}
