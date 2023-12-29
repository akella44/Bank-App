package project.bankapp.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WrongCardNumberException extends Exception {
    public WrongCardNumberException(String message){
        super(message);
    }
}
