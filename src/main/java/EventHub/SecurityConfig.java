package EventHub;

import EventHub.helpers.RestAuthenticationEntryPoint;
import EventHub.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private UserService userDetailsService;

    @PostConstruct
    public void completeSetup() {
        userDetailsService = applicationContext.getBean(UserService.class);
    }

//    @Bean
//    public SecurityFilterChain eventsfilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth -> auth.requestMatchers("/events")
//                        .authenticated()
//                        .requestMatchers(HttpMethod.GET, "/events/getall").hasAnyRole("ADMIN", "MODERATOR", "ORGANIZER", "USER")
//                        .requestMatchers(HttpMethod.POST, "/events/add").hasAnyRole("ADMIN", "MODERATOR", "ORGANIZER")
//                        .requestMatchers(HttpMethod.PUT, "/events/update").hasAnyRole("ADMIN", "MODERATOR", "ORGANIZER")
//                        .requestMatchers(HttpMethod.DELETE, "/events/delete").hasAnyRole("ADMIN", "MODERATOR", "ORGANIZER")
//                        .anyRequest().permitAll())
//                .formLogin(form -> form.permitAll())
//                .httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.authenticationEntryPoint(authenticationEntryPoint))
//                .csrf(AbstractHttpConfigurer::disable);
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll() // Pozwól na dostęp do wszystkich żądań
                .and()
                .csrf(csrf -> csrf.disable()) // Wyłącz ochronę przed atakami CSRF
                .formLogin(login -> login.disable()) // Wyłącz formularz logowania
                .httpBasic(basic -> basic.disable());

        return http.build();
    }

    @Bean
    public UserDetailsManager users(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setAuthenticationManager(authenticationManager);
        return jdbcUserDetailsManager;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void establishUsers()
//    {
//        userDetailsService.saveUser(new User("michal", passwordEncoder().encode("michal"), "ROLE_USER"));
//        userDetailsService.saveUser(new User("jan", passwordEncoder().encode("jan"), "ROLE_USER"));
//        userDetailsService.saveUser(new User("admin", passwordEncoder().encode("admin"), "ROLE_ADMIN"));
//        userDetailsService.saveUser(new User("kamil", passwordEncoder().encode("kamil"), "ROLE_ORGANIZER"));
//        userDetailsService.saveUser(new User("wacek", passwordEncoder().encode("wacek"), "ROLE_USER"));
//        userDetailsService.saveUser(new User("kuba", passwordEncoder().encode("kuba"), "ROLE_ORGANIZER"));
//    }
}
