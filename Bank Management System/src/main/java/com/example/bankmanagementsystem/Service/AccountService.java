package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.DTO.AccountDTO;
import com.example.bankmanagementsystem.DTO.AccountODTO;
import com.example.bankmanagementsystem.Model.Account;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Repository.AccountRepository;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public void addAccount(Integer id,AccountDTO accountDTO){
        Customer customer=customerRepository.findCustomerById(id);
        if (customer==null) throw new ApiException("Customer not found");

        Account account=new Account();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setBalance(accountDTO.getBalance());
        account.setIsActive(false);
        account.setCustomer(customer);

        accountRepository.save(account);
    }

    public void update(Integer customerId,Integer id, AccountDTO accountDTO){
        Account account=accountRepository.findAccountById(id);
        Customer customer=customerRepository.findCustomerById(customerId);
        if (account==null) throw new ApiException("Account not found");
        if (customer==null) throw new ApiException("Customer not found");
        if (!account.getCustomer().getId().equals(customerId))
            throw new ApiException("Unauthorized to update this account");
        if (account.getAccountNumber().equals(accountDTO.getAccountNumber()))
            throw new ApiException("Can't update account number please create a new account");
        account.setBalance(accountDTO.getBalance());

        accountRepository.save(account);
    }

    public void delete(Integer customerId, Integer id){
        Customer customer=customerRepository.findCustomerById(customerId);
        if (customer==null)throw new ApiException("Customer not found");
        Account account=accountRepository.findAccountById(id);
        if (account==null) throw new ApiException("Account not found");
        if (!account.getCustomer().getId().equals(customerId))
            throw new ApiException("Unauthorized to delete this account");

        account.setCustomer(null);
        accountRepository.delete(account);
    }

    public void ActivateAccount(Integer customerId,Integer id){
        Customer customer=customerRepository.findCustomerById(customerId);
        if (customer==null) throw new ApiException("Customer not found");
        Account account=accountRepository.findAccountById(id);
        if (account==null) throw new ApiException("Account not found");
        if (!account.getCustomer().getId().equals(customerId))
            throw new ApiException("This account doesn't belong to the user "+customer.getUser().getUsername());

        account.setIsActive(true);
        accountRepository.save(account);
    }

    public AccountODTO viewAccountDetails(Integer customerId,Integer id){
        Customer customer=customerRepository.findCustomerById(customerId);
        if (customer==null) throw new ApiException("Customer not found");
        Account account=accountRepository.findAccountById(id);
        if (account==null) throw new ApiException("Account not found");
        if (!account.getCustomer().getId().equals(customerId))
            throw new ApiException("Unauthorized to view this account");

        return new AccountODTO(account.getAccountNumber(),account.getBalance(),account.getIsActive());
    }

    public List<AccountODTO> viewUserAccounts(Integer customerId){
        Customer customer=customerRepository.findCustomerById(customerId);
        if (customer==null) throw new ApiException("Customer not found");

        return convertAccountsToDTO(accountRepository.findAccountByCustomer(customer));
    }

    public String deposit(Integer customerId,Integer id,Double amount){
        if (amount<0)
            throw new ApiException("amount must be a positive number");
        Customer customer=customerRepository.findCustomerById(customerId);
        if (customer==null) throw new ApiException("Customer not found");
        Account account=accountRepository.findAccountById(id);
        if (account==null) throw new ApiException("Account not found");
        if (!account.getCustomer().getId().equals(customerId))
            throw new ApiException("You can't deposit to this account please try to transfer");
        if (!account.getIsActive()) throw new ApiException("Account is not activated yet please contact customer support");

        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
        return "Your new balance is: "+account.getBalance();
    }

    public String withdraw(Integer customerId,Integer id,Double amount){
        if (amount<0)
            throw new ApiException("amount must be a positive number");
        Customer customer=customerRepository.findCustomerById(customerId);
        if (customer==null) throw new ApiException("Customer not found");
        Account account=accountRepository.findAccountById(id);
        if (account==null) throw new ApiException("Account not found");
        if (!account.getCustomer().getId().equals(customerId))
            throw new ApiException("You can't withdraw from this account");
        if (!account.getIsActive()) throw new ApiException("Account is not activated yet please contact customer support");

        if (account.getBalance()-amount<0)
            throw new ApiException("Not enough money in this account");

        account.setBalance(account.getBalance()-amount);

        return "Your new balance is: "+account.getBalance();
    }

    public String transfer(Integer customerId,Integer senderId, Integer receiverId,Double amount){
        if (amount<0)
            throw new ApiException("amount must be a positive number");
        Customer customer=customerRepository.findCustomerById(customerId);
        if (customer==null) throw new ApiException("Customer not found");
        Account account=accountRepository.findAccountById(senderId);
        if (account==null) throw new ApiException("Sender Account not found");
        if (!account.getCustomer().getId().equals(customerId))
            throw new ApiException("You can't transfer from this account");
        Account receiverAccount=accountRepository.findAccountById(receiverId);
        if (receiverAccount==null) throw new ApiException("Receiver account not found");
        if (!account.getIsActive()) throw new ApiException("Account is not activated yet please contact customer support");

        if (account.getBalance()-amount<0)
            throw new ApiException("Not enough money in this account");

        account.setBalance(account.getBalance()-amount);
        receiverAccount.setBalance(receiverAccount.getBalance()+amount);
        accountRepository.save(account);
        accountRepository.save(receiverAccount);

        return "Your new balance is: "+account.getBalance();
    }

    public void blockBankAccount(Integer id){
        Account account=accountRepository.findAccountById(id);

        if (account==null)throw new ApiException("Account not found");

        account.setIsActive(false);
        accountRepository.save(account);
    }

    public List<AccountODTO> convertAccountsToDTO(Collection<Account> accounts){
        List<AccountODTO> accountODTOS=new ArrayList<>();

        for (Account a:accounts){
            accountODTOS.add(new AccountODTO(a.getAccountNumber(),a.getBalance(),a.getIsActive()));
        }

        return accountODTOS;
    }
}
