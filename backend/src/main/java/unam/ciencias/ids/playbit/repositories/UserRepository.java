package unam.ciencias.ids.playbit.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unam.ciencias.ids.playbit.models.User;


public interface UserRepository extends CrudRepository<User,Integer> {
    @Query(nativeQuery = true , value = "SELECT * FROM Usuario WHERE email = :emailparam")
    public List<User> getUserByEmail(@Param("emailparam") String email);


    @Query(nativeQuery = true, value = "SELECT * FROM Usuario WHERE username = :usernameparam")
    public Optional<User> findByUsername(@Param("usernameparam") String username);

    
    @Query(nativeQuery = true, value = "SELECT * FROM Usuario WHERE username = :usernameparam")
    public List<User> existsByUsername(@Param("usernameparam") String username);



}
