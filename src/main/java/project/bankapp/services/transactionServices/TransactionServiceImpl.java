package project.bankapp.services.transactionServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.bankapp.dao.BillDao;
import project.bankapp.dao.TransactionDao;
import project.bankapp.dto.models.TransactionModel;
import project.bankapp.dto.requests.CashTransferRequest;
import project.bankapp.dto.requests.ReplenishmentRequest;
import project.bankapp.dto.requests.TransactionsGetResponse;
import project.bankapp.enums.TransactionType;
import project.bankapp.exceptions.*;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService{
    private final BillDao billDao;
    private final TransactionDao transactionDao;
    @Override
    public void transfer(CashTransferRequest cashTransferRequest, String email) throws WrongCardNumberException, WrongCurrencyTypeException, InvalidArgException, InvalidOpertaionException, NotEnoughCashException {
        //check if both cards exists
        if((!billDao.getUserCards(email).contains(cashTransferRequest.getFromCard())) ||
                !billDao.isBillExistByCard(cashTransferRequest.getToCard()))
            throw new WrongCardNumberException("Wrong card number");
        //check if both bills has same currency types
        if(!(billDao.getBillByCard(cashTransferRequest.getFromCard()).getCurrency() ==
                billDao.getBillByCard(cashTransferRequest.getToCard()).getCurrency()))
            throw new WrongCurrencyTypeException("Currency of bills not same");
        try{
            billDao.decreaseBillBalance(cashTransferRequest.getValue(), cashTransferRequest.getFromCard());
        }
        catch (InvalidOpertaionException ex){
            throw new NotEnoughCashException("Not enough cash for transfer");
        }
        billDao.increaseBillBalance(cashTransferRequest.getValue(), cashTransferRequest.getToCard());

        TransactionModel transaction = TransactionModel.builder()
                .transactionType(TransactionType.TRANSFER)
                .value(cashTransferRequest.getValue())
                .fromBillEntity(billDao.getBillByCard(cashTransferRequest.getFromCard()))
                .toBillEntity(billDao.getBillByCard(cashTransferRequest.getToCard()))
                .build();

        transactionDao.addNewTransaction(transaction);
    }

    @Override
    public void replenish(ReplenishmentRequest replenishmentRequest, String email) throws WrongCardNumberException, InvalidArgException {
        if(!billDao.getUserCards(email).contains(replenishmentRequest.getCardNumber()))
            throw new WrongCardNumberException("Wrong card number");

        billDao.increaseBillBalance(
                replenishmentRequest.getValue(),
                replenishmentRequest.getCardNumber()
        );

        TransactionModel transaction = TransactionModel.builder()
                .transactionType(TransactionType.REPLENISHMENT)
                .value(replenishmentRequest.getValue())
                .toBillEntity(billDao.getBillByCard(replenishmentRequest.getCardNumber()))
                .build();

        transactionDao.addNewTransaction(transaction);
    }

    @Override
    public List<TransactionsGetResponse> getTransactions(String email) {
        return transactionDao.getUserTransactions(email).stream()
                .map(TransactionsGetResponse::fromTransactionModel)
                .toList();
    }
}
