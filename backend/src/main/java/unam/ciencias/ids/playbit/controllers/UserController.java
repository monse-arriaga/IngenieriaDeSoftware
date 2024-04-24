package unam.ciencias.ids.playbit.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import jakarta.validation.Valid;
import unam.ciencias.ids.playbit.models.ERole;
import unam.ciencias.ids.playbit.models.Role;
import unam.ciencias.ids.playbit.models.Tournament;
import unam.ciencias.ids.playbit.models.User;
import unam.ciencias.ids.playbit.payload.request.EnrollRequest;
import unam.ciencias.ids.playbit.payload.request.LoginRequest;
import unam.ciencias.ids.playbit.payload.request.SignupRequest;
import unam.ciencias.ids.playbit.payload.response.JwtResponse;
import unam.ciencias.ids.playbit.payload.response.MessageResponse;
import unam.ciencias.ids.playbit.repositories.RoleRepository;
import unam.ciencias.ids.playbit.repositories.UserRepository;
import unam.ciencias.ids.playbit.security.jwt.JwtUtils;
import unam.ciencias.ids.playbit.security.services.UserDetailsImpl;
import unam.ciencias.ids.playbit.services.EnrollServices;
import unam.ciencias.ids.playbit.services.TournamentServices;
import unam.ciencias.ids.playbit.services.UserServices;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserServices userServices;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EnrollServices enrollServices;


    @Autowired
    UserRepository userRepository;

    @Autowired
    TournamentServices tournamentServices;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/hola/") 
    public String hello(){
        return "hola";
    }


    @PostMapping("/enroll/")
    public ResponseEntity<?> addTournament(@RequestBody EnrollRequest enrollRequest){
        Tournament tournament = enrollRequest.getTournament();
        User user = enrollRequest.getUser();
        
        if(!tournamentServices.findTournament(tournament.getName())){
            throw new IllegalArgumentException("Tournament doesn't exists.");
        }

        if(!enrollServices.enrollUser(user, tournament)){
            throw new IllegalArgumentException("torneo lleno o jugador esta inscrito ya");
        }

        return ResponseEntity.ok( new MessageResponse("jugador inscrito."));
    }

    @PostMapping("/login/")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        //List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        //.collect(Collectors.toList());

        return ResponseEntity
        .ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail() /* , roles */));
    }

    @PostMapping("/create/")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        List<User> userList = userRepository.existsByUsername(signUpRequest.getUsername());
        if (userList.size() > 0) {
         return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        userList = userRepository.getUserByEmail(signUpRequest.getEmail());

        if (userList.size() > 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        
        
        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()), signUpRequest.getBornDate());
        /* 
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
            
        if (strRoles == null) {
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        } else {
        strRoles.forEach(role -> {
            switch (role) {
            case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
            case "mod":
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

            break;
            default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            } 
        });
    } */

    //user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }


    // @PostMapping("/create/")
    // public void createUser(@Valid @RequestBody User user){
    //     if (!userServices.createUser(user))
    //         throw new IllegalArgumentException("El email ya esta usado.");
    // }

    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleCustomException(IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(illegalArgumentException.getMessage());
    }
}
