package sigris.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sigris.Exceptions.ServiceExceptions;
import sigris.Service.PlayerStatsService;
import sigris.security.isOpenStats;

@RestController
@RequestMapping("/player/stats")
public class PlayerStatsController {
    private final PlayerStatsService playerStatsService;

    public PlayerStatsController(PlayerStatsService playerStatsService) {
        this.playerStatsService = playerStatsService;
    }

    @Operation(summary = "Get count of wins by nickname")
    @GetMapping("/{nickname}/wins")
    @isOpenStats
    public ResponseEntity<Integer> getPlayerStatsWins(@PathVariable("nickname") String nickName,
                                  @AuthenticationPrincipal UserDetails user) {
        try{
            int wins = playerStatsService.getWinsCount(nickName);
            return ResponseEntity.ok(wins);
        } catch (ServiceExceptions.ThereIsNoPlayerWithThatName e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get count of losses by nickname")
    @GetMapping("/{nickname/losses")
    @isOpenStats
    public ResponseEntity<Integer> getPlayerStatsLosses(@PathVariable("nickname") String nickName,
                                                       @AuthenticationPrincipal UserDetails user){
        try {
            int lossesCount = playerStatsService.getLossesCount(nickName);
            return ResponseEntity.ok(lossesCount);
        } catch (ServiceExceptions.ThereIsNoPlayerWithThatName e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get count of draws by nickname")
    @GetMapping("/{nickname/draws")
    @isOpenStats
    public ResponseEntity<Integer> getPlayerStatsDraws(@PathVariable("nickname") String nickName,
                                                        @AuthenticationPrincipal UserDetails user){
        try {
            int drawsCount = playerStatsService.getDrawCount(nickName);
            return ResponseEntity.ok(drawsCount);
        } catch (ServiceExceptions.ThereIsNoPlayerWithThatName e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get winrate by nickname")
    @GetMapping("/{nickname/winrate")
    @isOpenStats
    public ResponseEntity<Double> getPlayerStatsWinrate(@PathVariable("nickname") String nickName,
                                                        @AuthenticationPrincipal UserDetails user){
        try {
            double winrate = playerStatsService.getWinrate(nickName);
            return ResponseEntity.ok(winrate);
        } catch (ServiceExceptions.ThereIsNoPlayerWithThatName e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get total count of games by nickname")
    @GetMapping("/{nickname/totalGames")
    @isOpenStats
    public ResponseEntity<Integer> getPlayerStatsTotalGames(@PathVariable("nickname") String nickName,
                                                        @AuthenticationPrincipal UserDetails user){
        try {
            int totalGames = playerStatsService.getTotalGamesCount(nickName);
            return ResponseEntity.ok(totalGames);
        } catch (ServiceExceptions.ThereIsNoPlayerWithThatName e) {
            return ResponseEntity.notFound().build();
        }
    }
}
