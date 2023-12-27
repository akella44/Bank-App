package project.bankapp.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.bankapp.entities.TransactionEntity;
import project.bankapp.entities.UserEntity;
import project.bankapp.repositories.TransactionRepository;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class TransactionDao {
    private final TransactionRepository transactionRepository;

    public List<TransactionEntity> getUserTransactions(UserEntity userEntity){
        return Stream.concat(transactionRepository.findByFromUserEntity(userEntity).stream(),
                transactionRepository.findByToUserEntity(userEntity).stream())
                .toList();
    }
}
