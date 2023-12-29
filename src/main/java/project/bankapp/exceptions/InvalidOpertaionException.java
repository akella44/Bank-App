package project.bankapp.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidOpertaionException extends Exception{
    public InvalidOpertaionException(String message){
        super(message);
    }
}
