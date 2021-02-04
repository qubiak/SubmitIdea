package pl.qubiak.SubmitIdeas;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.qubiak.SubmitIdeas.Model.Ideas.Ideas;
import pl.qubiak.SubmitIdeas.Model.Users.AppUser;
import pl.qubiak.SubmitIdeas.Repo.AppUserRepo;
import pl.qubiak.SubmitIdeas.Repo.IdeasRepo;

@Configuration
public class Start {

    public Start(AppUserRepo appUserRepo, IdeasRepo ideasRepo, PasswordEncoder passwordEncoder) {
        AppUser appUserJanusz = new AppUser();
        appUserJanusz.setUsername("Janusz");
        appUserJanusz.setPassword(passwordEncoder.encode("Janusz123"));
        appUserJanusz.setRole("ROLE_ADMIN");
        appUserJanusz.setMail("test@janusz.pl");
        appUserJanusz.setEnabled(true);

        AppUser appUserMirek = new AppUser();
        appUserMirek.setUsername("Mirek");
        appUserMirek.setPassword(passwordEncoder.encode("Mirek123"));
        appUserMirek.setMail("test@mirek.pl");
        appUserMirek.setRole("ROLE_MOD");
        appUserMirek.setEnabled(true);

        AppUser appUserBogdan = new AppUser();
        appUserBogdan.setUsername("Bogdan");
        appUserBogdan.setPassword(passwordEncoder.encode("Bogdan123"));
        appUserBogdan.setMail("test@bogdan.pl");
        appUserBogdan.setRole("ROLE_USER");
        appUserBogdan.setEnabled(true);

        AppUser appUserKrzysiek = new AppUser();
        appUserKrzysiek.setUsername("Krzysiek");
        appUserKrzysiek.setPassword(passwordEncoder.encode("Krzysiek123"));
        appUserKrzysiek.setMail("test@krzysiek.pl");
        appUserKrzysiek.setRole("ROLE_USER");
        appUserKrzysiek.setEnabled(false);

        appUserRepo.save(appUserJanusz);
        appUserRepo.save(appUserMirek);
        appUserRepo.save(appUserBogdan);
        appUserRepo.save(appUserKrzysiek);

        Ideas ideas1 = new Ideas();
        ideas1.setIdea("pierwszy pomysł");
        ideas1.setAuthor("autor1");
        ideas1.setAccepted(false);

        Ideas ideas2 = new Ideas();
        ideas2.setIdea("drugi pomysł");
        ideas2.setAuthor("autor2");
        ideas2.setAccepted(true);

        ideasRepo.save(ideas1);
        ideasRepo.save(ideas2);
    }
}
