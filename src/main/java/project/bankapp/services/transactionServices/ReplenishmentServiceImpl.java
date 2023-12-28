package project.bankapp.services.transactionServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.bankapp.dao.BillDao;
import project.bankapp.dto.requests.ReplenishmentRequest;
import project.bankapp.exceptions.InvalidArgException;
import project.bankapp.exceptions.WrongCardNumberException;


@Service
@RequiredArgsConstructor
@Slf4j
public class ReplenishmentServiceImpl implements ReplenishmentService {
    private final BillDao billDao;
    @Override
    public void topUp(ReplenishmentRequest replenishmentRequest, String email) throws WrongCardNumberException {
        if(!billDao.getUserCards(email).contains(replenishmentRequest.getCardNumber()))
            throw new WrongCardNumberException();
        try {
            billDao.increaseBillBalance(
                    replenishmentRequest.getValue(),
                    replenishmentRequest.getCardNumber()
            );
        }
        catch (InvalidArgException ex){
            log.info("Error during replenishment");
        }
    }
}
