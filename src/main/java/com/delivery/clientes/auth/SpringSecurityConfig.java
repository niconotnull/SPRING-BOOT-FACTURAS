package com.delivery.clientes.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService usuarioService;

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager()throws Exception{
        return  super.authenticationManager();
    }

    // Se utiliza @Bean por que se requiere registrar el objeto que esta retornando el método
    // como un componente de Spring por que más adelante se utilizara en otra configuración
    // y la forma de registrar este objeto que retorna este método es a a través del @Bean
    // Se registra en el contenedor de spring
    // Por lo tanto despues se puede inyectar con @Autowired y utilizar en cualquier otra clase
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

//    @Override
//    public void configure(final WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/**");
//    }

}
