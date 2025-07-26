package sigris.Model.PlayersDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerStatsDTO {
    private Long playerId;

    private int winsCount;
    private int lossesCount;
    private int drawsCount;
    private int totalGamesCount;
}
