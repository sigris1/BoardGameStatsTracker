package sigris.Exceptions;

public class OwnedGameExceptions {
    public static class PlayerDoesntOwnThisGame extends Exception {
        public PlayerDoesntOwnThisGame() {super ("Player doesn't own this game");}
    }
}
