package project.bankapp.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WrongCurrencyTypeException extends Exception {
    public WrongCurrencyTypeException(String message){
        super(message);
    }
}
