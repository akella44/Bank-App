package project.bankapp.services.transactionServices;

import project.bankapp.dto.requests.CashTransferRequest;
import project.bankapp.exceptions.InvalidArgException;
import project.bankapp.exceptions.NotEnoughCashException;
import project.bankapp.exceptions.WrongCardNumberException;
import project.bankapp.exceptions.WrongCurrencyTypeException;

public interface CashTransferService {
    public void transfer(CashTransferRequest cashTransferRequest, String email) throws WrongCardNumberException, WrongCurrencyTypeException, NotEnoughCashException, InvalidArgException;
}
