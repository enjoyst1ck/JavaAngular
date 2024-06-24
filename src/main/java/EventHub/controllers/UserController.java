package EventHub.controllers;

import EventHub.dtos.LoginDto;
import EventHub.dtos.RegisterDto;
import EventHub.dtos.UserDto;
import EventHub.models.User;
import EventHub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/account/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto){
        return ResponseEntity.ok(service.register(userDto));
    }

    @PostMapping("/account/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto){
        return ResponseEntity.ok(service.login(userDto));
    }

    @PostMapping("/account/refresh")
    public ResponseEntity<UserDto> refreshToken(@RequestBody UserDto userDto){
        return ResponseEntity.ok(service.refreshToken(userDto));
    }
}