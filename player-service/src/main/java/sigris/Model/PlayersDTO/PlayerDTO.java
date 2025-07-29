package sigris.Model.PlayersDTO;

import lombok.Builder;
import lombok.Data;
import sigris.Model.PlayersModel.Role;

@Data
@Builder
public class PlayerDTO {
    private String nickName;
    private String name;
    private String surname;
    private String email;
    private Role role;
}
