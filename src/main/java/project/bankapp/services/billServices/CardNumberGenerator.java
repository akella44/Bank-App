package project.bankapp.services.billServices;

public interface CardNumberGenerator {
    public String generate(String bin, int length);
}
