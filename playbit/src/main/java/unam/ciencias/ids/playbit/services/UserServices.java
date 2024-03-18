package unam.ciencias.ids.playbit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unam.ciencias.ids.playbit.models.User;
import unam.ciencias.ids.playbit.repositories.UserRepository;

@Service
public class UserServices {
    
    @Autowired
    UserRepository userRepository;


    public boolean createUser(User user){
        List<User> users = userRepository.getUserByEmail(user.getEmail());
        
        if(users.size() > 0){
            return false;
        }

        userRepository.save(user);
        return true;        
    }



    public boolean login(User user){
        List<User> users = userRepository.getUserByEmail(user.getEmail());
        if (users.size() == 1) {
            String receivedpasswd = user.getPassword();
            String usrpasswd = users.get(0).getPassword();
            return receivedpasswd.equals(usrpasswd);
        }

        return false;
    }
}
