package com.security.config;

import com.security.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()   //Disable Cross-Site Request Forgery Token that sets in cookies for web browsers but if need to access API from non browser then need to disable it
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()  //Here added Token to Cooking as we can see it in now in non brosers also
                .authorizeRequests()
                .antMatchers("/public/**").hasRole("NORMAL")
                .antMatchers("/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        *//*auth.inMemoryAuthentication().withUser("mayur").password(this.passwordEncoder().encode("mayur@2255")).roles("NORMAL");
        auth.inMemoryAuthentication().withUser("komal").password(this.passwordEncoder().encode("komal@2255")).roles("ADMIN");*//*

        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }*/

@Configuration
@EnableWebSecurity
public class MySecurityConfig{
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  //Disable Cross-Site Request Forgery Token that sets in cookies for web browsers but if need to access API from non browser then need to disable it
                .authorizeHttpRequests((authz)->authz
                        .requestMatchers("/public/**").hasRole("NORMAL")
                        .requestMatchers("/users/**").permitAll()
                        .anyRequest()
                        .authenticated()

                )
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }

   @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
   }

    //To use plain text as password
   /* @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }*/

    //To use password using Encoding
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    /*--------------------------------------NOTES---------------------------------*/
    /*
    *   If we need not used to antMacher then we can use @PreAutherize(hasRole("ROLE")) on Perticular method and can write @EnableGlobalMethodSecurity(PrePostEnable=true)
    *
    * */

}
