package net.fmattaperdomo.usecase.exception;

public class TypeLoanNotFoundException extends RuntimeException{
    public TypeLoanNotFoundException(String message) {
        super(message);
    }
}
