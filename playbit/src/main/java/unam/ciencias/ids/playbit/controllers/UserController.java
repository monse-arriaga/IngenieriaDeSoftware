package unam.ciencias.ids.playbit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unam.ciencias.ids.playbit.models.User;
import unam.ciencias.ids.playbit.services.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserServices userServices;

    @GetMapping("/hola/") 
    public String hello(){
        return "hola";
    }


    @PostMapping("/create/")
    public void createUser(@RequestBody User user){
        if(!userServices.createUser(user))
            throw new IllegalArgumentException("El email ya esta usado.");
    }

    @PostMapping("/login/")
    public void login(@RequestBody User user){
        if(!userServices.login(user))
            throw new IllegalArgumentException("Email o contraseña incorrecta");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleCustomException(IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(illegalArgumentException.getMessage());
    }
}
