package EventHub.services;

import EventHub.dtos.LoginDto;
import EventHub.dtos.RegisterDto;
import EventHub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Base64;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    private JdbcTemplate jdbcTemplate;
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    public Boolean login(LoginDto loginDto) {
        UserDetails user = loadUserByUsername(loginDto.getUsername());
        if (user != null) {
            String hashedPassword = user.getPassword();
            if (hashedPassword != null) {
                if (passwordEncoder().matches(loginDto.getPassword(), hashedPassword)) {
                    return true;
                }
                return false; //"Niepoprawne hasło";
            }
        }
        return false;
    }

    public Boolean register(RegisterDto registerDto) {
        UserDetails user = loadUserByUsername(registerDto.getUsername());
        if (user != null) {
            String hashedPassword = user.getPassword();
            if (hashedPassword != null) {
                if (passwordEncoder().matches(registerDto.getPassword(), hashedPassword)) {
                    return true;
                }
                return false; //"Niepoprawne hasło";
            }
        }
        return false;
    }

    public boolean authenticateFromHeader(String authHeader) {
        if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith("Basic ")) {
            return false;
        }

        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        String[] values = credentials.split(":", 2);

        if (values.length != 2) {
            return false;
        }

        String username = values[0];
        String password = values[1];

        String query = "SELECT password_hash FROM users WHERE username = ?";
        String hashedPassword = jdbcTemplate.queryForObject(query, String.class, username);
        if (hashedPassword != null) {
            return passwordEncoder().matches(password, hashedPassword);
        }
        return false;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean checkUser(String authHeader) {
        // Sprawdzenie, czy nagłówek autoryzacyjny został dostarczony
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return false;
        }

        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        String[] values = credentials.split(":", 2);

        return true;//passwordEncoder.matches(password, hashedPassword);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return  userRepository.findByUsername(username)
//                    .map(SecurityUser::new)
//                    .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
//    }

/*    public UserDetails currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserDetails) {
            return (UserDetails) auth.getPrincipal();
        }
        return null;
    }
*/
    public void saveUser (EventHub.models.User user) {
        userRepository.save(user);
    }

    /*public UserDto login(LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        User user = (User) loadUserByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Invalid username or password") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }

        UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getRole());

        return userDto;
    }*/
}
