package cz.fel.cvut.hamrasan.gardener.exceptions;

public class AlreadyLoginException extends Exception {
    public AlreadyLoginException() {
        super("You are already login.");
    }
}
