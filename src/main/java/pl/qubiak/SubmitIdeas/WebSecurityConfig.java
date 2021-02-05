package pl.qubiak.SubmitIdeas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.qubiak.SubmitIdeas.Service.UserDetailsServiceImpl;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserDetailsServiceImpl userDetailsService;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//endpointy przekierowują na formatkę login. Zalogowani mają dostęp do endpointów zgodnie ze specyfikacją.
//czy obsługuje grupy endpointów /all/start musze napisac czy wystarczy /all?
        http.csrf().disable();
        http.headers().disable();
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/all").hasAnyRole("USER", "MOD", "ADMIN")
                .antMatchers("/mod").hasAnyRole("MOD", "ADMIN")
                .antMatchers("/admin/all").hasRole("ADMIN");
        http.authorizeRequests()
                .antMatchers("/all").authenticated()
                .antMatchers("/admin/all").authenticated()
                .antMatchers("/mod").authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/all/start")
                .and()
                .logout().permitAll();

    }

        /*
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/all").permitAll()
                .antMatchers("/mod").hasAnyRole("MOD", "ADMIN")
                .antMatchers("/admin").hasRole("ADMIN")
                .and()
                .formLogin().permitAll().defaultSuccessUrl("/start" + "")
                .and()
                .logout().permitAll();


        http.csrf().disable();
        http.headers().disable();
        http.authorizeRequests()
                .antMatchers("/all").permitAll()
                .antMatchers("/mod").hasAnyRole("MOD", "ADMIN")
                .antMatchers("/admin").hasRole("ADMIN")
                .and()
                .formLogin().defaultSuccessUrl("/start" +
                "");
    }

         */
}

