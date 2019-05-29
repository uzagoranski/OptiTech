package si.feri.pkm.optitech.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .antMatcher("/**")
//                .authorizeRequests()
//                .antMatchers("/", "/index.html")
//                .permitAll()
//                .anyRequest()
//                .authenticated();
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
        web.ignoring().antMatchers("/vendor/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/img/**");
        web.ignoring().antMatchers("/scss/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/gulpfile.js");
        web.ignoring().antMatchers("/package.json");
        web.ignoring().antMatchers("/package-lock.json");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/carDetails", "carsList").hasAnyRole("USER", "ADMIN")
                .antMatchers("/index").permitAll()
                .antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN")
                .antMatchers("/addNewEmployee").hasAnyRole().anyRequest().authenticated()
                .and().formLogin().loginPage("/index").permitAll().defaultSuccessUrl("/dodajanjeDogodka")
                .and().logout().permitAll();

        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.inMemoryAuthentication().withUser("admin").password("admin").authorities("ROLE_USER").and()
                .withUser("javainuse").password("javainuse").authorities("ROLE_USER", "ROLE_ADMIN");
    }
}