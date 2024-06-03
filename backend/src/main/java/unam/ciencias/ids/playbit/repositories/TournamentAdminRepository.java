package unam.ciencias.ids.playbit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import unam.ciencias.ids.playbit.models.TournamentAdmin;

public interface TournamentAdminRepository extends CrudRepository<TournamentAdmin, Integer>{
        @Query(nativeQuery = true, value = "SELECT * FROM tournament_admin WHERE usuario_id = :idparam")
    public List<TournamentAdmin> getTournamentAdminByUser(@Param("idparam") int id);

    @Query(nativeQuery = true, value = "SELECT * FROM tournament_admin WHERE torneo_id = :idparam")
    public List<TournamentAdmin> getTournamentAdminByTournament(@Param("idparam") String id);
    

    @Query(nativeQuery = true, value = "SELECT * FROM tournament_admin WHERE (usuario_id, torneo_id) IN ((:idparam,:torneoid))")
    public List<TournamentAdmin> getUserTournamentAdmins(@Param("idparam") int id, @Param("torneoid") String name);

}
