package sigris.Service;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigris.Exceptions.ServiceExceptions;
import sigris.Model.PlayersModel.PlayerStats;
import sigris.Repository.PlayerStatsRepository;

import java.util.Optional;

@Data
@Builder
@Service
public class PlayerStatsService {
    private PlayerStatsRepository playerStatsRepository;

    @Autowired
    public PlayerStatsService(PlayerStatsRepository playerStatsRepository) {
        this.playerStatsRepository = playerStatsRepository;
    }

    public void save(PlayerStats playerStats) {
        playerStatsRepository.save(playerStats);
    }

    private PlayerStats getPlayerStats(String playerNickName) throws ServiceExceptions.ThereIsNoPlayerWithThatName {
        Optional<PlayerStats> playerStats = playerStatsRepository.findByNickName(playerNickName);
        if (playerStats.isPresent()) {
            return playerStats.get();
        } else {
            throw new ServiceExceptions.ThereIsNoPlayerWithThatName();
        }
    }

    public int getWinsCount(String playerNickName) throws ServiceExceptions.ThereIsNoPlayerWithThatName {
        PlayerStats playerStats = getPlayerStats(playerNickName);
        return playerStats.getWinsCount();
    }

    public int getDrawCount(String playerNickName) throws ServiceExceptions.ThereIsNoPlayerWithThatName {
        PlayerStats playerStats = getPlayerStats(playerNickName);
        return playerStats.getDrawsCount();
    }

    public int getLossesCount(String playerNickName) throws ServiceExceptions.ThereIsNoPlayerWithThatName {
        PlayerStats playerStats = getPlayerStats(playerNickName);
        return playerStats.getLossesCount();
    }

    public int getTotalGamesCount(String playerNickName) throws ServiceExceptions.ThereIsNoPlayerWithThatName {
        PlayerStats playerStats = getPlayerStats(playerNickName);
        return playerStats.getTotalGamesCount();
    }

    public double getWinrate(String playerNickName) throws ServiceExceptions.ThereIsNoPlayerWithThatName {
        PlayerStats playerStats = getPlayerStats(playerNickName);
        return playerStats.getWinrate();
    }
}
