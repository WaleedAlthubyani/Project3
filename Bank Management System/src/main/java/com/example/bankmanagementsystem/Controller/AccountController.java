package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.DTO.AccountDTO;
import com.example.bankmanagementsystem.DTO.AccountODTO;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/get-my-accounts")
    public ResponseEntity<ApiResponse<List<AccountODTO>>> viewUserAccounts(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(new ApiResponse<>(accountService.viewUserAccounts(myUser.getId())));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addAccount(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid AccountDTO accountDTO){
        accountService.addAccount(myUser.getId(), accountDTO);
        return ResponseEntity.status(201).body(new ApiResponse<>("Account added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> update(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer id, @RequestBody @Valid AccountDTO accountDTO){
        accountService.update(myUser.getId(), id,accountDTO);
        return ResponseEntity.status(200).body(new ApiResponse<>("Account updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer id){
        accountService.delete(myUser.getId(), id);
        return ResponseEntity.status(200).body(new ApiResponse<>("Account deleted successfully"));
    }

    @PutMapping("/activate/{customer-id}/{account-id}")
    public ResponseEntity<ApiResponse<String>> ActivateAccount(@PathVariable(name = "customer-id") Integer customerId,@PathVariable(name = "account-id") Integer accountId){
        accountService.ActivateAccount(customerId,accountId);
        return ResponseEntity.status(200).body(new ApiResponse<>("Account activated successfully"));
    }

    @GetMapping("/view-account-details/{id}")
    public ResponseEntity<ApiResponse<AccountODTO>> viewAccountDetails(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(accountService.viewAccountDetails(myUser.getId(), id)));
    }

    @PutMapping("/deposit/{id}/{amount}")
    public ResponseEntity<ApiResponse<String>> deposit(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer id,@PathVariable Double amount){
        return ResponseEntity.status(200).body(new ApiResponse<>(accountService.deposit(myUser.getId(), id,amount)));
    }

    @PutMapping("/withdraw/{id}/{amount}")
    public ResponseEntity<ApiResponse<String>> withdraw(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer id,@PathVariable Double amount){
        return ResponseEntity.status(200).body(new ApiResponse<>(accountService.withdraw(myUser.getId(), id,amount)));
    }

    @PutMapping("/transfer/from/{account-id}/to/{receiver-id}/amount/{amount}")
    public ResponseEntity<ApiResponse<String>> transfer(@AuthenticationPrincipal MyUser myUser, @PathVariable(name = "account-id") Integer accountId,@PathVariable(name = "receiver-id") Integer receiverId,@PathVariable Double amount){
        return ResponseEntity.status(200).body(new ApiResponse<>(accountService.transfer(myUser.getId(), accountId,receiverId,amount)));
    }

    @PutMapping("/block-bank-account/{id}")
    public ResponseEntity<ApiResponse<String>> blockBankAccount(@PathVariable Integer id){
        accountService.blockBankAccount(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("account blocked successfully"));
    }
}
