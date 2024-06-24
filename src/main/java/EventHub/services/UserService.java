package EventHub.services;

import EventHub.dtos.UserDto;
import EventHub.models.User;
import EventHub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDto register(UserDto registrationRequest){
        UserDto res = new UserDto();

        try {
            User user = new User();
            user.setRole(registrationRequest.getRole());
            user.setUsername(registrationRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            User result = userRepo.save(user);

            if (result.getId() > 0) {
                res.setOurUsers(result);
                res.setMessage("User Saved");
                res.setStatusCode(200);
            }

        } catch (Exception e){
            res.setStatusCode(500);
            res.setError(e.getMessage());
        }

        return res;
    }

    public UserDto login(UserDto loginDto){
        UserDto res = new UserDto();

        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

            var user = userRepo.findByUsername(loginDto.getUsername());
            var jwt = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
            res.setStatusCode(200);
            res.setToken(jwt);
            res.setRole(user.getRole());
            res.setRefreshToken(refreshToken);
            res.setExpirationTime("24Hr");
            res.setMessage("Successfully Logged In");

        }catch (Exception e){
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
        }

        return res;
    }

    public UserDto refreshToken(UserDto refreshTokenReqiest){
        UserDto res = new UserDto();
        try{
            String username = jwtService.extractUsername(refreshTokenReqiest.getToken());
            User users = userRepo.findByUsername(username);
            if (jwtService.isTokenValid(refreshTokenReqiest.getToken(), users)) {
                var jwt = jwtService.generateToken(users);
                res.setStatusCode(200);
                res.setToken(jwt);
                res.setRefreshToken(refreshTokenReqiest.getToken());
                res.setExpirationTime("24Hr");
                res.setMessage("Successfully Refreshed Token");
            }
            res.setStatusCode(200);
            return res;

        }catch (Exception e){
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
            return res;
        }
    }


    public UserDto getAllUsers() {
        UserDto res = new UserDto();

        try {
            List<User> result = userRepo.findAll();
            if (!result.isEmpty()) {
                res.setOurUsersList(result);
                res.setStatusCode(200);
                res.setMessage("Successful");
            } else {
                res.setStatusCode(404);
                res.setMessage("No users found");
            }
            return res;
        } catch (Exception e) {
            res.setStatusCode(500);
            res.setMessage("Error message: " + e.getMessage());
            return res;
        }
    }


    public UserDto getUsersById(Integer id) {
        UserDto res = new UserDto();
        try {
            User usersById = userRepo.findById(Long.valueOf(id)).orElseThrow(() -> new RuntimeException("User Not found"));
            res.setOurUsers(usersById);
            res.setStatusCode(200);
            res.setMessage("Users with id '" + id + "' found successfully");
        } catch (Exception e) {
            res.setStatusCode(500);
            res.setMessage("Error occurred: " + e.getMessage());
        }
        return res;
    }


    public UserDto deleteUser(Integer userId) {
        UserDto reqRes = new UserDto();
        try {
            Optional<User> userOptional = userRepo.findById(Long.valueOf(userId));
            if (userOptional.isPresent()) {
                userRepo.deleteById(Long.valueOf(userId));
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    public UserDto updateUser(Integer userId, User updatedUser) {
        UserDto reqRes = new UserDto();
        try {
            Optional<User> userOptional = userRepo.findById(Long.valueOf(userId));
            if (userOptional.isPresent()) {
                User existingUser = userOptional.get();
                existingUser.setUsername(updatedUser.getUsername());
                existingUser.setRole(updatedUser.getRole());

                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                User savedUser = userRepo.save(existingUser);
                reqRes.setOurUsers(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }


    public UserDto getMyInfo(String username){
        UserDto res = new UserDto();
        try {
            User userOptional = userRepo.findByUsername(username);
            if (userOptional != null) {
                res.setOurUsers(userOptional);
                res.setStatusCode(200);
                res.setMessage("Successfuly");
            } else {
                res.setStatusCode(404);
                res.setMessage("User not found for update");
            }

        }catch (Exception e){
            res.setStatusCode(500);
            res.setMessage("Error: " + e.getMessage());
        }

        return res;
    }
}
