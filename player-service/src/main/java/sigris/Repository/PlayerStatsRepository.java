package sigris.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sigris.Model.PlayersModel.PlayerStats;

import java.util.Optional;

@Repository
public interface PlayerStatsRepository extends JpaRepository<PlayerStats, String> {
    Optional<PlayerStats> findByNickName(String nickName);
}
