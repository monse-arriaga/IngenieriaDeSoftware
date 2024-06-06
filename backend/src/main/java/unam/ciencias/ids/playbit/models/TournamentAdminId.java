package unam.ciencias.ids.playbit.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Embeddable
public class TournamentAdminId implements Serializable {
    @Column(name = "usuario_id")
    private int userID;
    @Column(name = "torneo_id")
    private String tournamentID;
}
