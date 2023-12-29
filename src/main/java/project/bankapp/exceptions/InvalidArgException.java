package project.bankapp.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidArgException extends Exception{
    public InvalidArgException(String message){
        super(message);
    }
}
