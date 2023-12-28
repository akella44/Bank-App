package project.bankapp.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import project.bankapp.dto.models.TransactionModel;
import project.bankapp.entities.BillEntity;
import project.bankapp.entities.TransactionEntity;
import project.bankapp.entities.UserEntity;
import project.bankapp.repositories.BillRepository;
import project.bankapp.repositories.TransactionRepository;
import project.bankapp.repositories.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
@Slf4j
public class TransactionDao {
    private final TransactionRepository transactionRepository;
    private final BillRepository billRepository;
    private final UserRepository userRepository;
    public List<TransactionModel> getUserTransactions(String email){
        UserEntity userEntity = userRepository.findByEmail(email);

        Stream<TransactionEntity> fromUserTransactions = Optional.ofNullable(transactionRepository.findByFromUserEntity(userEntity))
                .map(List::stream)
                .orElse(Stream.empty());

        Stream<TransactionEntity> toUserTransactions = transactionRepository.findByToUserEntity(userEntity).stream();

        return Stream.concat(fromUserTransactions, toUserTransactions)
                .filter(Objects::nonNull)
                .map(TransactionModel::fromEntity)
                .toList();
    }
    public void addNewTransaction(TransactionModel transactionModel){
        BillEntity fromBillEntity = null;
        BillEntity toBillEntity = null;

        if (transactionModel.getFromBillEntity() != null) {
            fromBillEntity = billRepository.findByCardNumber(transactionModel.getFromBillEntity().getCardNumber());
        }
        if (transactionModel.getToBillEntity() != null) {
            toBillEntity = billRepository.findByCardNumber(transactionModel.getToBillEntity().getCardNumber());
        }

        TransactionEntity entityTransaction = TransactionEntity.builder()
                .transactionType(transactionModel.getTransactionType())
                .value(transactionModel.getValue())
                .fromBillEntity(fromBillEntity)
                .toBillEntity(toBillEntity)
                .fromUserEntity(fromBillEntity != null ? fromBillEntity.getOwner() : null)
                .toUserEntity(toBillEntity != null ? toBillEntity.getOwner() : null)
                .id(UUID.randomUUID())
                .build();

        transactionRepository.save(entityTransaction);
    }
}
