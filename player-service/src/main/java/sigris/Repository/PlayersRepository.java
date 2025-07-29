package sigris.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sigris.Model.PlayersModel.Player;

import java.util.Optional;

@Repository
public interface PlayersRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByNickName(String Name);
}
