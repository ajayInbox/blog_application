package com.aj.blog.user;

import com.aj.blog.auth.RegisterReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApplicationUserController {

    private final ApplicationUserService userService;

    public ApplicationUserController(ApplicationUserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUsers(){
       return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<AppUser> getUserWithEmail(@RequestParam String email){
        return new ResponseEntity<>(userService.getUserWithEmail(email), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<AppUser> getUserWithID(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserWithID(userId), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<String> registerUserAsAdmin(@RequestBody RegisterReq registerReq){
        return new ResponseEntity<>(userService.registerUserAsAdmin(registerReq), HttpStatus.CREATED);
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUserByEmail( @RequestParam String email){
        return new ResponseEntity<>(userService.deleteUserByEmail(email), HttpStatus.OK);
    }
}
