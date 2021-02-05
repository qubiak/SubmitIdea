package pl.qubiak.SubmitIdeas.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.qubiak.SubmitIdeas.Model.Users.AppUser;
import pl.qubiak.SubmitIdeas.Repo.AppUserRepo;
import pl.qubiak.SubmitIdeas.Repo.Dao.Ideas;
import pl.qubiak.SubmitIdeas.Repo.Dao.User;
import pl.qubiak.SubmitIdeas.Repo.TokenRepo;
import pl.qubiak.SubmitIdeas.Service.UserService;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminUserController {

    private UserService userService;
    private AppUserRepo appUserRepo;
    private TokenRepo tokenRepo;
    @Autowired
    public User user;
    @Autowired
    public Ideas ideas;


    public AdminUserController(UserService userService, AppUserRepo appUserRepo, TokenRepo tokenRepo) {
        this.userService = userService;
        this.appUserRepo = appUserRepo;
        this.tokenRepo = tokenRepo;
    }

    //ADMIN
    @RequestMapping("/delateUser")
    @ResponseBody
    public String deleteUser(
            @RequestParam int id) {
        user.deleteUser(id);
        return "delete user with id: " + id;
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


}
