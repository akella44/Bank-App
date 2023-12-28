package project.bankapp.services.billServices;

import project.bankapp.dto.models.UserModel;
import project.bankapp.dto.requests.BillCreationRequest;
import project.bankapp.dto.requests.BillsGetResponse;

import java.util.List;

public interface BillService {
    public void createBill(BillCreationRequest billCreationRequest, UserModel owner);
    public List<BillsGetResponse> getUserBills(String email);
}
