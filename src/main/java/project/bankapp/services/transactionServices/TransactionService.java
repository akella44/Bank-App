package project.bankapp.services.transactionServices;

import project.bankapp.dto.models.TransactionModel;
import project.bankapp.dto.requests.CashTransferRequest;
import project.bankapp.dto.requests.ReplenishmentRequest;
import project.bankapp.dto.requests.TransactionsGetResponse;
import project.bankapp.exceptions.*;

import java.util.List;

public interface TransactionService {
    public void transfer(CashTransferRequest cashTransferRequest, String email) throws
            WrongCardNumberException, WrongCurrencyTypeException, NotEnoughCashException, InvalidArgException, InvalidOpertaionException;
    public void replenish(ReplenishmentRequest replenishmentRequest, String email) throws WrongCardNumberException, InvalidArgException;

    public List<TransactionsGetResponse> getTransactions(String email);
}
