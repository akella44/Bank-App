package project.bankapp.services.bills;

public interface CardNumberGenerator {
    public String generate(String bin, int length);
}
