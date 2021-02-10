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

        http.csrf().disable();
        http.headers().disable();
        http.authorizeRequests()
                .antMatchers("/hello").authenticated() //USER
                .antMatchers("/getAcceptedIdeas").authenticated() //USER
                .antMatchers("/addIdeas").authenticated() //USER
                .antMatchers("/getUnacceptedIdeas").authenticated() //MOD
                .antMatchers("/deleteIdeaById").authenticated() //MOD
                .antMatchers("/acceptedIdeas").authenticated() //MOD
                .antMatchers("/deleteUser").authenticated() //ADMIN
                .antMatchers("/changeRoleToMod").authenticated() //ADMIN
                .antMatchers("/all").authenticated() //ADMIN
                .and()
                .formLogin().defaultSuccessUrl("/hello");
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/hello").hasAnyRole("USER", "MOD", "ADMIN")
                .antMatchers("/getAcceptedIdeas").hasAnyRole("USER", "MOD", "ADMIN")
                .antMatchers("/addIdeas").hasAnyRole("USER", "MOD", "ADMIN")
                .antMatchers("/getUnacceptedIdeas").hasAnyRole("MOD", "ADMIN")
                .antMatchers("/deleteIdeaById").hasAnyRole("MOD", "ADMIN")
                .antMatchers("/acceptedIdeas").hasAnyRole("MOD", "ADMIN")
                .antMatchers("/deleteUser").hasRole("ADMIN")
                .antMatchers("/changeRoleToMod").hasRole("ADMIN")
                .antMatchers("/all").hasRole("ADMIN")
                .and()
                .logout().permitAll();
    }
}
