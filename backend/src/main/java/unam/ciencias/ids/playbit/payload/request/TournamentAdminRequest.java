package unam.ciencias.ids.playbit.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unam.ciencias.ids.playbit.models.Tournament;
import unam.ciencias.ids.playbit.models.User;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class TournamentAdminRequest {
    @NotBlank
    private Tournament tournament;

    @NotBlank
    private User user;

}
