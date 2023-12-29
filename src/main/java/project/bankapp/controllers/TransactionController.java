package project.bankapp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.bankapp.dto.requests.CashTransferRequest;
import project.bankapp.dto.requests.ReplenishmentRequest;
import project.bankapp.dto.requests.TransactionsGetResponse;
import project.bankapp.exceptions.*;
import project.bankapp.services.securityServices.jwt.JwtService;
import project.bankapp.services.transactionServices.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/bill")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {
    private final JwtService jwtService;
    private final TransactionService transactionService;
    @PostMapping("/replenishment")
    @PreAuthorize("hasAuthority('USER')")
    HttpStatus topUpUserBill(@RequestBody ReplenishmentRequest replenishmentRequest,
                             HttpServletRequest request) throws WrongCardNumberException, InvalidArgException {
        transactionService.replenish(replenishmentRequest, jwtService.getUserModelByReqWithToken(request).getEmail());
        return HttpStatus.OK;
    }

    @PostMapping("/transfer")
    @PreAuthorize("hasAuthority('USER')")
    HttpStatus transferMoney(@RequestBody CashTransferRequest cashTransferRequest,
                             HttpServletRequest request) throws WrongCardNumberException, WrongCurrencyTypeException,
            NotEnoughCashException, InvalidOpertaionException, InvalidArgException {

        transactionService.transfer(cashTransferRequest, jwtService.getUserModelByReqWithToken(request).getEmail());
        return HttpStatus.OK;
    }

    @GetMapping("/transactions")
    @PreAuthorize("hasAuthority('USER')")
    List<TransactionsGetResponse> transferMoney(HttpServletRequest request) {
           return transactionService.getTransactions(
                   jwtService.getUserModelByReqWithToken(request).getEmail());
    }
}
