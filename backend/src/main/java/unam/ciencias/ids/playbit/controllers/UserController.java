package unam.ciencias.ids.playbit.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import unam.ciencias.ids.playbit.models.Tournament;
import unam.ciencias.ids.playbit.models.User;
import unam.ciencias.ids.playbit.payload.request.EnrollDeleteRequest;
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
import java.util.LinkedList;


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



    @GetMapping("/findbyname/{username}")
    public List<User> findByName(@PathVariable String username){
        List<User> list = new LinkedList<>();

        Optional<User> usr = userRepository.findByUsername(username);

        if(!usr.isPresent())
            throw new IllegalArgumentException("user not found");
        
        list.add(usr.get());

        return list;
    }


    @GetMapping("/findbyid/{id}")
    public List<User> findById(@PathVariable int id){
        List<User> list = new LinkedList<>();
        Optional<User> usr = userRepository.findById(id);
        if(!usr.isPresent())
            throw new IllegalArgumentException("user not found");
        
        list.add(usr.get());

        return list;
    }


    @PostMapping("/enroll/{username}/{tournament_name}")
    public ResponseEntity<?> addTournament(@PathVariable String username, @PathVariable String tournament_name){
        List<Tournament> tournamentList = tournamentServices.findTournamentByName(tournament_name);
        List<User> userList = userRepository.existsByUsername(username);
        
        if(tournamentList.size() == 0){
            throw new IllegalArgumentException("Tournament doesn't exists.");
        }

        if(userList.size() == 0){
            throw new IllegalArgumentException("Usuario no existente");
        }
        
        if(!enrollServices.enrollUser(userList.get(0), tournamentList.get(0))){
            throw new IllegalArgumentException("torneo lleno o jugador esta inscrito ya");
        }

        return ResponseEntity.ok( new MessageResponse("jugador inscrito."));
    }

    @PostMapping("/edit/")
    public ResponseEntity<?> editUser(@RequestBody User user){

        Optional<User> userToFind = userRepository.findById(user.getID());
        if (!userToFind.isPresent()) {
            throw new IllegalArgumentException("User doesn't exists.");
        }
        String passwdEncoded = encoder.encode(user.getPassword());
        user.setPassword(passwdEncoded);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Usuario editado."));

    }

    @PostMapping("/delete_enrollment/")
    public ResponseEntity<?> deleteUserEnrollment(@RequestBody EnrollDeleteRequest enrollDeleteRequest){
        if(!enrollServices.deleteEnrollment(enrollDeleteRequest.getUser(), enrollDeleteRequest.getTournament()))
            throw new IllegalArgumentException("user not enrolled in tournament");
        return ResponseEntity.ok(new MessageResponse("enrollment deleted."));
    }



    @GetMapping("/findbyid/{id}")
    public User findUserById(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent())
            throw new IllegalArgumentException("User doesn't exists.");

        return user.get();
    }

    @GetMapping("/find/{username}")
    public List<User> findUserByUsername(@PathVariable String username){
        List<User> users = userRepository.existsByUsername(username);

        if(users.size() == 0)
            throw new IllegalArgumentException("user not found");

        return users;
    }

    @GetMapping("/tournaments_enrolled/{userid}")
    public List<String> getUserTournaments(@PathVariable int userid) {
        
        Optional<User> user = userRepository.findById(userid);
        
        if(!user.isPresent()){
            throw new IllegalArgumentException("User doesn't exists.");
        }
        
        List<String> tournamentsEnrolled = enrollServices.getUserTournaments(userid);

        if(tournamentsEnrolled.size() == 0){
            throw new IllegalArgumentException("User is not enrolled in any tournament.");
        }

        return tournamentsEnrolled;
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







    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleCustomException(IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(illegalArgumentException.getMessage());
    }
}
