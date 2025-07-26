package sigris.Model.PlayersModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "playerStats")
public class PlayerStats {
    @Id
    private Long playerId;

    private int winsCount;
    private int lossesCount;
    private int drawsCount;
    private int totalGamesCount;

    public double getWinrate() {
        return (double) winsCount / totalGamesCount * 100;
    }
}
