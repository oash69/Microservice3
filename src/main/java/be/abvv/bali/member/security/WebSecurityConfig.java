package be.abvv.bali.member.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Web Security.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
/*
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    //UserDetails build = User.withUsername("admin").password("admin"));
    UserDetailsService userDetailsService = null;
    http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET)
            .hasRole("ADMIN")
            .antMatchers("/v1/**")
            .hasAnyRole("ADMIN")
            .antMatchers("/user/**")
            .hasAnyRole("USER", "ADMIN")
            .antMatchers("/v1/**")
            .anonymous()
            .anyRequest()
            .authenticated()
            .and()
            // .userDetailsService()
            .httpBasic()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    http
//            .authorizeHttpRequests((requests) -> requests
//                    .antMatchers(HttpMethod.GET, "/v1/**")
//                    .permitAll()
//                    .anyRequest().authenticated()
//            );

    return http.build();
  }
*/
  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user =
            User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password("admin")
                    .roles("ADMIN")
                    .build();

    return new InMemoryUserDetailsManager(user);
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
            .passwordEncoder(passwordEncoder())
            .withUser("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("ADMIN");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


}
