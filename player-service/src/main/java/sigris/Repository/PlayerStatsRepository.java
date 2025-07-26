package sigris.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sigris.Model.PlayersModel.PlayerStats;

import java.util.Optional;

public interface PlayerStatsRepository extends JpaRepository<PlayerStats, String> {
    Optional<PlayerStats> findByNickName(String nickName);
}
