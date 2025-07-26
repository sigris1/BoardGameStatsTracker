package sigris.Model.PlayersDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerDTO {
    private String nickName;
    private String name;
    private String surname;
    private String email;
}
