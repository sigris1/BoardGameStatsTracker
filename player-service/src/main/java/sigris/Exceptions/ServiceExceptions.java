package sigris.Exceptions;

public class ServiceExceptions {
    public static class ThereIsNoPlayerWithThatName extends Exception{
        public ThereIsNoPlayerWithThatName() { super ("There Is No Player With That Name");}
    }
}
