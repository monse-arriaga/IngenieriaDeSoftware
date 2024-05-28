package unam.ciencias.ids.playbit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unam.ciencias.ids.playbit.models.Group;

public interface GroupRepository extends CrudRepository<Group,Integer> {
    
    @Query(nativeQuery = true,value = "SELECT * FROM grupo WHERE id = :groupid")
    public List<Group> getGroupById(@Param("groupid") int id);
}
