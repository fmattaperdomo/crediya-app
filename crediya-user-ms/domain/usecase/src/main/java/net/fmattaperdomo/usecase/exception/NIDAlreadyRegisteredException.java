package net.fmattaperdomo.usecase.exception;

public class NIDAlreadyRegisteredException extends RuntimeException{
    public NIDAlreadyRegisteredException(String message) {
        super(message);
    }
}
