package EventHub;

import EventHub.Security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;



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
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request->request
                        .requestMatchers("/auth/**", "/public/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/user/**").hasAnyAuthority("USER")
                        .requestMatchers("/adminuser/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/artists/getall").permitAll()
                        //.requestMatchers("/artists/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/staff/getall").permitAll()
                        //.requestMatchers("/staff/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/venues/getall").permitAll()
                        //.requestMatchers("/venues/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers("/events/**").permitAll()
                        .requestMatchers("/artists/**").permitAll()
                        .requestMatchers("/staff/**").permitAll()
                        .requestMatchers("/venues/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                );
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void establishUsers()
//    {
//        userDetailsService.saveUser(new User("michal", passwordEncoder().encode("michal"), "ROLE_USER"));
//        userDetailsService.saveUser(new User("jan", passwordEncoder().encode("jan"), "ROLE_USER"));
//        userDetailsService.saveUser(new User("admin", passwordEncoder().encode("admin"), "ROLE_ADMIN"));
//        userDetailsService.saveUser(new User("kamil", passwordEncoder().encode("kamil"), "ROLE_USER"));
//        userDetailsService.saveUser(new User("wacek", passwordEncoder().encode("wacek"), "ROLE_USER"));
//        userDetailsService.saveUser(new User("kuba", passwordEncoder().encode("kuba"), "ROLE_ADMIN"));
//    }
}
