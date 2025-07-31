package sigris.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sigris.Exceptions.OwnedGameExceptions;
import sigris.Model.OwnedGameDTO.DTOManager;
import sigris.Model.OwnedGameDTO.OwnedGameDTO;
import sigris.Model.OwnedGameModel.Condition;
import sigris.Model.OwnedGameModel.OwnedGame;
import sigris.Service.OwnedGameService;

import java.time.LocalDate;

@RestController
@RequestMapping("/ownedGames")
public class OwnedGameController {
    private final OwnedGameService ownedGameService;

    @Autowired
    public OwnedGameController(OwnedGameService ownedGameService) {
        this.ownedGameService = ownedGameService;
    }

    @Operation(summary = "Add new owned game")
    @PostMapping
    public ResponseEntity<OwnedGameDTO> addPlayer(@RequestBody OwnedGameDTO ownedGameDTO) {
        OwnedGame ownedGame = DTOManager.toOwnedGame(ownedGameDTO);
        ownedGameService.save(ownedGame);
        return ResponseEntity.status(HttpStatus.CREATED).body(DTOManager.fromOwnedGame(ownedGame));
    }

    @Operation(summary = "Is that player own this game")
    @GetMapping("/{nickname}/{gameName}")
    public ResponseEntity<OwnedGameDTO> isPlayerOwnThisGame(@PathVariable("nickname") String nickname, @PathVariable("gameName") String gameName) {
        try {
            OwnedGame ownedGame = ownedGameService.getOwnedGame(nickname, gameName);
            return ResponseEntity.ok(DTOManager.fromOwnedGame(ownedGame));
        } catch (OwnedGameExceptions.PlayerDoesntOwnThisGame e){
            return ResponseEntity.notFound().build();
        }
    }

   @Operation(summary = "Get date of purchase")
   @GetMapping("/{nickname}/{gameName}/dateOfPurchase")
   public ResponseEntity<LocalDate> getDateOfPurchase(@PathVariable("nickname") String nickname, @PathVariable("gameName") String gameName) {
        try {
            LocalDate date = ownedGameService.getDateOfPurchaseOwnedGame(nickname, gameName);
            return ResponseEntity.ok(date);
        } catch (OwnedGameExceptions.PlayerDoesntOwnThisGame e){
            return ResponseEntity.notFound().build();
        }
   }

    @Operation(summary = "Get condition")
    @GetMapping("/{nickname}/{gameName}/condition")
    public ResponseEntity<String> getCondition(@PathVariable("nickname") String nickname, @PathVariable("gameName") String gameName) {
        try {
            Condition condition = ownedGameService.getConditionOwnedGame(nickname, gameName);
            return ResponseEntity.ok(condition.toString());
        } catch (OwnedGameExceptions.PlayerDoesntOwnThisGame e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get localized status")
    @GetMapping("/{nickname}/{gameName}/localized")
    public ResponseEntity<Boolean> getLocalizedStatus(@PathVariable("nickname") String nickname, @PathVariable("gameName") String gameName) {
        try {
            boolean islocalized = ownedGameService.getLocalizedTypeOwnedGame(nickname, gameName);
            return ResponseEntity.ok(islocalized);
        } catch (OwnedGameExceptions.PlayerDoesntOwnThisGame e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get expansion status")
    @GetMapping("/{nickname}/{gameName}/exspansion")
    public ResponseEntity<Boolean> getExpansionStatus(@PathVariable("nickname") String nickname, @PathVariable("gameName") String gameName) {
        try {
            boolean isExspansion = ownedGameService.getExpansionTypeOwnedGame(nickname, gameName);
            return ResponseEntity.ok(isExspansion);
        } catch (OwnedGameExceptions.PlayerDoesntOwnThisGame e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get edition number")
    @GetMapping("/{nickname}/{gameName}/editionNumber")
    public ResponseEntity<Integer> getEditionNumber(@PathVariable("nickname") String nickname, @PathVariable("gameName") String gameName) {
        try {
            int number = ownedGameService.getEditionNumberOwnedGame(nickname, gameName);
            return ResponseEntity.ok(number);
        } catch (OwnedGameExceptions.PlayerDoesntOwnThisGame e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Sell owned game to another player")
    @GetMapping("/{nickname}/{gameName}/sell/{anotherNickname}")
    public ResponseEntity<Boolean> getDateOfPurchase(@PathVariable("nickname") String nickname,
                                                       @PathVariable("gameName") String gameName,
                                                       @PathVariable("anotherNickname") String anotherNickname) {
        try {
            ownedGameService.sellOwnedGameToAnotherPlayer(nickname, gameName, anotherNickname);
            return ResponseEntity.ok(true);
        } catch (OwnedGameExceptions.PlayerDoesntOwnThisGame e){
            return ResponseEntity.notFound().build();
        }
    }
}
