package si.feri.pkm.optitech.Configuration;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
        web.ignoring().antMatchers("/static/**");
        web.ignoring().antMatchers("/vendor/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/fonts/poppins/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/WEB-INF/jsp/template/navigation.jsp");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/carDetails", "carsList", "comparison", "statsAI").hasAnyRole("USER", "ADMIN")
                .antMatchers("/index").permitAll()
                .antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN")
                .antMatchers("/addNewEmployee").hasAnyRole().anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/index")
                .and().logout()    //logout configuration
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        http.csrf().disable();
    }
}