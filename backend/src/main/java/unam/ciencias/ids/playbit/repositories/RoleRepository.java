package unam.ciencias.ids.playbit.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import unam.ciencias.ids.playbit.models.ERole;
import unam.ciencias.ids.playbit.models.Role;
import unam.ciencias.ids.playbit.models.User;



@Repository
public interface RoleRepository extends   JpaRepository<Role, Integer>  {
    Optional<Role> findByName(ERole name);
}