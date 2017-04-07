package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by MIHONE on 3/27/2017.
 */

@Configuration
@EnableWebSecurity
public class SecureLogin extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserCredentialsData userCredentialsData;



    @Override
    public void configure(HttpSecurity httpSecurity) throws  Exception {

        httpSecurity.csrf().disable();
        httpSecurity
                .authorizeRequests().antMatchers("/WEB-INF/pages/**").permitAll()
                .antMatchers("*/update_employee").hasRole("ADMIN")
                .antMatchers("*/view_employee").hasRole("ADMIN")
                .antMatchers("/admin/*").hasRole("ADMIN")
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/home")
                .loginPage("/login").usernameParameter("u").passwordParameter("p").
                permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
                .and().exceptionHandling().accessDeniedPage("/WEB-INF/pages/403page.jsp");
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("/login");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws  Exception{
        auth.userDetailsService(this.userCredentialsData);
    }
}
