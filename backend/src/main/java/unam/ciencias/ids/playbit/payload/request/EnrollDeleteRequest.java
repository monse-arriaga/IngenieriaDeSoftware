package unam.ciencias.ids.playbit.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unam.ciencias.ids.playbit.models.Tournament;
import unam.ciencias.ids.playbit.models.User;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class EnrollDeleteRequest {
    @NotBlank
    private User user;

    @NotBlank
    private Tournament tournament;

}
