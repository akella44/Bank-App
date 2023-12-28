package project.bankapp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.bankapp.dto.models.BillModel;
import project.bankapp.dto.requests.BillCreationRequest;
import project.bankapp.dto.requests.BillsGetResponse;
import project.bankapp.services.bills.BillService;
import project.bankapp.services.security.jwt.JwtService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/bill")
@RequiredArgsConstructor
@Slf4j
public class BillController {
    private final BillService billService;
    private final JwtService jwtService;
    @GetMapping("")
    @PreAuthorize("hasAuthority('USER')")
    ResponseEntity<List<BillsGetResponse>> getUserBills(HttpServletRequest request){
        try {
            return ResponseEntity.ok(
                    billService.getUserBills(
                            jwtService.getUserModelByReqWithToken(request)
                                    .getEmail()
                    )
            );
        }
        catch (Exception ex){
            return null;
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('USER')")
    HttpStatus createBill(@RequestBody BillCreationRequest billCreationRequest,
                            HttpServletRequest request){

        try{
            log.info("Creating bill with info " + billCreationRequest.toString());
            billService.createBill(billCreationRequest, jwtService.getUserModelByReqWithToken(request));
            return HttpStatus.OK;
        }
        catch (Exception ex){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
