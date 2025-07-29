package sigris.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sigris.security.isAuthorized;
import sigris.Exceptions.ServiceExceptions;
import sigris.Model.PlayersDTO.DTOManager;
import sigris.Model.PlayersDTO.PlayerDTO;
import sigris.Model.PlayersModel.Player;
import sigris.Service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playersService;
    private final PasswordEncoder passwordEncoder;

    public PlayerController(PlayerService playerService, PasswordEncoder passwordEncoder) {
        this.playersService = playerService;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(summary = "Add new player")
    @PostMapping
    public ResponseEntity<PlayerDTO> addPlayer(@RequestBody PlayerDTO playerDTO, String password) {
        Player player = DTOManager.toPlayer(playerDTO);
        player.setPassword(passwordEncoder.encode(password));
        playersService.save(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(DTOManager.fromPlayer(player));
    }

    @Operation(summary = "Get player by nickname")
    @GetMapping("/{nickname}/player")
    @isAuthorized
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable("nickname") String nickName) {
        try {
            PlayerDTO playerDTO = playersService.getPlayerByNickName(nickName);
            return ResponseEntity.ok(playerDTO);
        }
        catch (ServiceExceptions.ThereIsNoPlayerWithThatName e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get player's email")
    @GetMapping("/{nickname}/email")
    @isAuthorized
    public ResponseEntity<String> getPlayersEmail(@PathVariable("nickname") String nickName) {
        try {
            String email = playersService.getEmail(nickName);
            return ResponseEntity.ok(email);
        }
        catch (ServiceExceptions.ThereIsNoPlayerWithThatName e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get player's name")
    @GetMapping("/{nickname}/name")
    @isAuthorized
    public ResponseEntity<String> getPlayersName(@PathVariable("nickname") String nickName) {
        try {
            String email = playersService.getName(nickName);
            return ResponseEntity.ok(email);
        }
        catch (ServiceExceptions.ThereIsNoPlayerWithThatName e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get player's surname")
    @GetMapping("/{nickname}/surname")
    @isAuthorized
    public ResponseEntity<String> getPlayersSurname(@PathVariable("nickname") String nickName) {
        try {
            String email = playersService.getSurName(nickName);
            return ResponseEntity.ok(email);
        }
        catch (ServiceExceptions.ThereIsNoPlayerWithThatName e) {
            return ResponseEntity.notFound().build();
        }
    }
}
