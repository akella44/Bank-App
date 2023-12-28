package project.bankapp.services.transactionServices;

import project.bankapp.dto.requests.ReplenishmentRequest;
import project.bankapp.exceptions.WrongCardNumberException;

public interface ReplenishmentService {
    public void  topUp(ReplenishmentRequest replenishmentRequest, String email) throws WrongCardNumberException;
}
