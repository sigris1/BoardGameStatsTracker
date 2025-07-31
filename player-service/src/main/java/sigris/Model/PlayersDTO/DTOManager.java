package sigris.Model.PlayersDTO;

import sigris.Model.PlayersModel.Player;
import sigris.Model.PlayersModel.PlayerStats;

public class DTOManager {
    public static PlayerDTO fromPlayer(Player player) {
        return PlayerDTO.builder()
                .nickName(player.getNickName())
                .name(player.getName())
                .surname(player.getSurname())
                .email(player.getEmail())
                .role(player.getRole())
                .accountType(player.getAccountType())
                .build();
    }

    public static Player toPlayer(PlayerDTO dto) {
        return Player.builder()
                .nickName(dto.getNickName())
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .role(dto.getRole())
                .accountType(dto.getAccountType())
                .build();
    }

    public static PlayerStatsDTO fromPlayerStats(PlayerStats playerStats) {
        return PlayerStatsDTO.builder()
                .playerId(playerStats.getPlayerId())
                .winsCount(playerStats.getWinsCount())
                .drawsCount(playerStats.getDrawsCount())
                .lossesCount(playerStats.getLossesCount())
                .totalGamesCount(playerStats.getTotalGamesCount())
                .build();
    }

    public static PlayerStats toPlayerStats(PlayerStats dto) {
        return PlayerStats.builder()
                .playerId(dto.getPlayerId())
                .winsCount(dto.getWinsCount())
                .drawsCount(dto.getDrawsCount())
                .lossesCount(dto.getLossesCount())
                .totalGamesCount(dto.getTotalGamesCount())
                .build();
    }
}
