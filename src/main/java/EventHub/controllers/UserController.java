package EventHub.controllers;

import EventHub.dtos.LoginDto;
import EventHub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService service;

    /*@GetMapping("/account/checkUser")
    public Boolean checkUser(@RequestHeader("Authorization") String authHeader) {
        return service.authenticateFromHeader(authHeader);
    }*/

    @PutMapping("/account/login")
    public Boolean login(@RequestBody LoginDto loginDto) {
        return service.login(loginDto);
    }
/*
    @GetMapping("/current")
    public UserDetails loadCurrentUser() {
        return service.currentUser();
    }
*/
//    @PostMapping("/login")
//    public UserDto login(@RequestBody LoginDto loginDto) {
//        return service.login(loginDto);
//    }

}