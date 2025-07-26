package sigris.Service;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import sigris.Exceptions.ServiceExceptions;
import sigris.Model.PlayersDTO.DTOManager;
import sigris.Model.PlayersDTO.PlayerDTO;
import sigris.Model.PlayersModel.Player;
import sigris.Repository.PlayersRepository;

import java.util.Optional;

@Builder
@Data
@Service
public class PlayerService {
    private PlayersRepository playersRepository;

    private Player getPlayer(String playerNickName) throws ServiceExceptions.ThereIsNoPlayerWithThatName {
        Optional<Player> player = playersRepository.findByNickName(playerNickName);
        if (player.isPresent()) {
            return player.get();
        } else {
            throw new ServiceExceptions.ThereIsNoPlayerWithThatName();
        }
    }

    @Autowired
    public PlayerService( PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    public void save(Player player) {
        playersRepository.save(player);
    }

    public PlayerDTO getPlayerByNickName(String playerNickName) throws ServiceExceptions.ThereIsNoPlayerWithThatName {
        Player player = getPlayer(playerNickName);
        return DTOManager.fromPlayer(player);
    }

    public String getName(String playerNickName) throws ServiceExceptions.ThereIsNoPlayerWithThatName {
        Player player = getPlayer(playerNickName);
        return player.getName();
    }

    public String getSurName(String playerNickName) throws ServiceExceptions.ThereIsNoPlayerWithThatName {
        Player player = getPlayer(playerNickName);
        return player.getSurname();
    }

    public String getEmail(String playerNickName) throws ServiceExceptions.ThereIsNoPlayerWithThatName {
        Player player = getPlayer(playerNickName);
        return player.getEmail();
    }
}
