package sigris.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sigris.Model.OwnedGameModel.OwnedGame;

import java.util.Optional;

@Repository
public interface OwnedGamesRepository extends JpaRepository<OwnedGame, Long> {
    Optional<OwnedGame> isOwnedGame(String playerNickName, String gameName);
}
