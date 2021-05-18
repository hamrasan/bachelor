package cz.fel.cvut.hamrasan.gardener.exceptions;

public class NotAllowedException extends Exception {
    public NotAllowedException() {
        super("Forbidden operation.");
    }
    public NotAllowedException(String message) {
        super(message);
    }
}
