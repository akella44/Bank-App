package project.bankapp.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotEnoughCashException extends Exception{
    public NotEnoughCashException(String message){
        super(message);
    }
}
