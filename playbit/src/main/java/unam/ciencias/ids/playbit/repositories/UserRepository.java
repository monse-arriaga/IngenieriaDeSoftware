package unam.ciencias.ids.playbit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unam.ciencias.ids.playbit.models.User;


public interface UserRepository extends CrudRepository<User,Integer> {
    @Query(nativeQuery = true , value = "SELECT * FROM userswe WHERE mail = :emailparam")
    public List<User> getUserByEmail(@Param("emailparam") String email);
}
