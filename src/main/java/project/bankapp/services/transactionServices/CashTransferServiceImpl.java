package project.bankapp.services.transactionServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.bankapp.dao.BillDao;
import project.bankapp.dao.TransactionDao;
import project.bankapp.dto.models.TransactionModel;
import project.bankapp.dto.requests.CashTransferRequest;
import project.bankapp.enums.TransactionType;
import project.bankapp.exceptions.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CashTransferServiceImpl implements CashTransferService{
    private final BillDao billDao;
    private final TransactionDao transactionDao;
    @Override
    public void transfer(CashTransferRequest cashTransferRequest, String email) throws WrongCardNumberException, WrongCurrencyTypeException, NotEnoughCashException, InvalidArgException {
        //check if both cards exists
        if((!billDao.getUserCards(email).contains(cashTransferRequest.getFromCard())) ||
                !billDao.isBillExistByCard(cashTransferRequest.getToCard()))
            throw new WrongCardNumberException();
        //check if both bills has same currency types
        if(!(billDao.getBillByCard(cashTransferRequest.getFromCard()).getCurrency() ==
                billDao.getBillByCard(cashTransferRequest.getToCard()).getCurrency()))
            throw new WrongCurrencyTypeException();

        try{
            billDao.decreaseBillBalance(cashTransferRequest.getValue(), cashTransferRequest.getFromCard());
        }
        catch (InvalidOpertaionException ex){
            throw new NotEnoughCashException();
        }

        try{
            billDao.increaseBillBalance(cashTransferRequest.getValue(), cashTransferRequest.getToCard());
        }
        catch (InvalidArgException ex){
            throw new InvalidArgException();
        }

        TransactionModel transaction = TransactionModel.builder()
                .transactionType(TransactionType.TRANSFER)
                .value(cashTransferRequest.getValue())
                .fromBillEntity(billDao.getBillByCard(cashTransferRequest.getFromCard()))
                .toBillEntity(billDao.getBillByCard(cashTransferRequest.getToCard()))
                .build();

        transactionDao.addNewTransaction(transaction);
    }
}
