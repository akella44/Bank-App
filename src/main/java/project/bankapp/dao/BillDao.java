package project.bankapp.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.bankapp.dto.models.BillModel;
import project.bankapp.entities.BillEntity;
import project.bankapp.repositories.BillRepository;
import project.bankapp.repositories.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BillDao {
    private final BillRepository billRepository;
    private final UserRepository userRepository;
    public List<BillModel> getUserBills(String email){
        List<BillEntity> billEntities = billRepository.findByOwner(userRepository.findByEmail(email));
        //convert to List<BillModeL> and return
        return billEntities.stream()
                .map(BillModel::fromEntity)
                .toList();
    }

    public void createNewBill(BillModel billModel){
        BillEntity billEntity = BillEntity.builder()
                .id(UUID.randomUUID())
                //getting UserEntity with userRep
                .owner(userRepository.findByEmail(billModel.getOwner().getEmail()))
                .value(billModel.getValue())
                .currency(billModel.getCurrency())
                .cardNumber(billModel.getCardNumber())
                .build();
        billRepository.save(billEntity);
    }
}
