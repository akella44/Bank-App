package project.bankapp.dto.models;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class BillModel {
    private UUID id;
    private UserModel owner;
    private String currency;
    private Long value;
}
