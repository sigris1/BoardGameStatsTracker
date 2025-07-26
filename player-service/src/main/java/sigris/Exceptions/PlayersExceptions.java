package sigris.Exceptions;

public class PlayersExceptions {
    public static class PlayerCantOwnNotHisGame extends Exception {
        public PlayerCantOwnNotHisGame() {super( "Player cannot own not his game");}
    }
}
