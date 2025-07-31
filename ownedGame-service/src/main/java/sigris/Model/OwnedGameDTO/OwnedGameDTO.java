package sigris.Model.OwnedGameDTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import sigris.Model.OwnedGameModel.Condition;

import java.time.LocalDate;

@Data
@Builder
@Getter
public class OwnedGameDTO {
    private String gameName;
    private String playerNickName;
    private LocalDate purchaseDate;
    private Condition condition;
    private boolean isLocalized;
    private boolean isExpansion;
    private int editionNumber;
}
