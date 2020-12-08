package br.com.devdojo.config;

import br.com.devdojo.service.CustomUserDatailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*Classe para config do security
* importante ficar atento as configurações/ anotation da classe*/
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private CustomUserDatailService customUserDatailService;

    /*configurando o método e o tipo de autenticação do http para cada requisição terá que estar autenticado*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*/protected/**").hasRole("USER")
                .antMatchers("/*/admin/**").hasRole("ADMIN")
                .and()
               .httpBasic()
                .and()
                .csrf()
                .disable();
        /*.csrf().disable() - desabilita segurança*/
    }

    //fazendo a autentição pela senha do BD
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //passando a senha que está criptografado
        auth.userDetailsService(customUserDatailService).passwordEncoder(new BCryptPasswordEncoder());
    }

     /*usando na mem´ria
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder aut) throws Exception {
        aut.inMemoryAuthentication().withUser("leandro").password("123").roles("USER")
                .and().withUser("admin").password("123").roles("USER","ADMIN");

    }*/
}
