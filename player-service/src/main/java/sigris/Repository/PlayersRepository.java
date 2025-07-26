package sigris.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sigris.Model.PlayersModel.Player;

import java.util.Optional;

public interface PlayersRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByNickName(String Name);
}
