package project.bankapp.model;


import java.util.UUID;

public class BillModel {
    private UUID id;
    private UserModel owner;
    private String currency;
    private Long value;
}
