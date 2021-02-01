package pl.qubiak.SubmitIdeas;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.qubiak.SubmitIdeas.Dao.UserDao;

@Configuration
public class Start {

    public Start(UserDao userDao, PasswordEncoder passwordEncoder) {
        
        userDao.addUser(true, "test", "ADMIN", "testowy", "bartektestowyjava@gmail.com");
    }
}
