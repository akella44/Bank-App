package project.bankapp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.bankapp.dto.requests.CashTransferRequest;
import project.bankapp.dto.requests.ReplenishmentRequest;
import project.bankapp.exceptions.InvalidArgException;
import project.bankapp.exceptions.InvalidOpertaionException;
import project.bankapp.exceptions.WrongCardNumberException;
import project.bankapp.services.securityServices.jwt.JwtService;
import project.bankapp.services.transactionServices.CashTransferService;
import project.bankapp.services.transactionServices.ReplenishmentService;

@RestController
@RequestMapping("/api/v1/user/bill")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {
    private final JwtService jwtService;
    private final ReplenishmentService billTopUpService;
    private final CashTransferService cashTransferService;
    @PostMapping("/replenishment")
    @PreAuthorize("hasAuthority('USER')")
    HttpStatus topUpUserBill(@RequestBody ReplenishmentRequest replenishmentRequest,
                             HttpServletRequest request) throws WrongCardNumberException {
        try{
            billTopUpService.topUp(replenishmentRequest, jwtService.getUserModelByReqWithToken(request).getEmail());
            return HttpStatus.OK;
        }
        catch (WrongCardNumberException ex){
            return HttpStatus.BAD_REQUEST;
        }
        catch (Exception ex){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @PostMapping("/transfer")
    @PreAuthorize("hasAuthority('USER')")
    HttpStatus transferMoney(@RequestBody CashTransferRequest cashTransferRequest,
                             HttpServletRequest request) {
        try{
            cashTransferService.transfer(cashTransferRequest, jwtService.getUserModelByReqWithToken(request).getEmail());
            return HttpStatus.OK;
        }
        catch (InvalidArgException ex){
            return HttpStatus.BAD_REQUEST;
        } catch (Exception ex){
            ex.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
