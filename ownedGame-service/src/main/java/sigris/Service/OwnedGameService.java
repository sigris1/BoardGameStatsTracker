package sigris.Service;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigris.Exceptions.OwnedGameExceptions;
import sigris.Model.OwnedGameModel.Condition;
import sigris.Model.OwnedGameModel.OwnedGame;
import sigris.Repository.OwnedGamesRepository;

import java.time.LocalDate;
import java.util.Optional;

@Builder
@Data
@Service
public class OwnedGameService {
    private OwnedGamesRepository ownedGamesRepository;

    @Autowired
    public OwnedGameService(OwnedGamesRepository ownedGamesRepository) {
        this.ownedGamesRepository = ownedGamesRepository;
    }

    public void save(OwnedGame ownedGame) {
        ownedGamesRepository.save(ownedGame);
    }

    public boolean isOwnedGame(String playerNickName, String gameName) {
        Optional<OwnedGame> ownedGame = ownedGamesRepository.isOwnedGame(playerNickName, gameName);
        return ownedGame.isPresent();
    }

    public OwnedGame getOwnedGame(String playerNickName, String gameName) throws OwnedGameExceptions.PlayerDoesntOwnThisGame {
        if (isOwnedGame(playerNickName, gameName)) {
            return ownedGamesRepository.isOwnedGame(playerNickName, gameName).get();
        } else {
            throw new OwnedGameExceptions.PlayerDoesntOwnThisGame();
        }
    }

    public LocalDate getDateOfPurchaseOwnedGame(String playerNickName, String gameName) throws OwnedGameExceptions.PlayerDoesntOwnThisGame {
        OwnedGame ownedGame = getOwnedGame(playerNickName, gameName);
        return ownedGame.getPurchaseDate();
    }

    public Condition getConditionOwnedGame(String playerNickName, String gameName) throws OwnedGameExceptions.PlayerDoesntOwnThisGame {
        OwnedGame ownedGame = getOwnedGame(playerNickName, gameName);
        return ownedGame.getCondition();
    }

    public boolean getLocalizedTypeOwnedGame(String playerNickName, String gameName) throws OwnedGameExceptions.PlayerDoesntOwnThisGame {
        OwnedGame ownedGame = getOwnedGame(playerNickName, gameName);
        return ownedGame.isLocalized();
    }

    public boolean getExpansionTypeOwnedGame(String playerNickName, String gameName) throws OwnedGameExceptions.PlayerDoesntOwnThisGame {
        OwnedGame ownedGame = getOwnedGame(playerNickName, gameName);
        return ownedGame.isExpansion();
    }

    public int getEditionNumberOwnedGame(String playerNickName, String gameName) throws OwnedGameExceptions.PlayerDoesntOwnThisGame {
        OwnedGame ownedGame = getOwnedGame(playerNickName, gameName);
        return ownedGame.getEditionNumber();
    }

    public void sellOwnedGameToAnotherPlayer(String playerNickName, String gameName, String newOwnerNickName) throws OwnedGameExceptions.PlayerDoesntOwnThisGame {
        OwnedGame ownedGame = getOwnedGame(playerNickName, gameName);
        ownedGame.setPlayerNickName(newOwnerNickName);
        ownedGamesRepository.save(ownedGame);
    }
}
