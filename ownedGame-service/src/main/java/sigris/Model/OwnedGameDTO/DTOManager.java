package sigris.Model.OwnedGameDTO;

import sigris.Model.OwnedGameModel.OwnedGame;

public class DTOManager {
    public static OwnedGameDTO fromOwnedGame(OwnedGame ownedGame) {
        return OwnedGameDTO.builder()
                .gameName(ownedGame.getGameName())
                .playerNickName(ownedGame.getPlayerNickName())
                .condition(ownedGame.getCondition())
                .editionNumber(ownedGame.getEditionNumber())
                .isExpansion(ownedGame.isExpansion())
                .isLocalized(ownedGame.isLocalized())
                .purchaseDate(ownedGame.getPurchaseDate())
                .build();
    }

    public static OwnedGame toOwnedGame(OwnedGameDTO ownedGameDTO) {
        return OwnedGame.builder()
                .gameName(ownedGameDTO.getGameName())
                .editionNumber(ownedGameDTO.getEditionNumber())
                .playerNickName(ownedGameDTO.getPlayerNickName())
                .purchaseDate(ownedGameDTO.getPurchaseDate())
                .isExpansion(ownedGameDTO.isExpansion())
                .isLocalized(ownedGameDTO.isLocalized())
                .condition(ownedGameDTO.getCondition())
                .build();
    }
}
