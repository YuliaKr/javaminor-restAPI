package nl.minor.week3.RestAPI.Controllers;

import io.swagger.annotations.ApiOperation;
import nl.minor.week3.RestAPI.Models.DomainModels.Account;
import nl.minor.week3.RestAPI.Models.Dtos.AccountDto;
import nl.minor.week3.RestAPI.Models.Dtos.AccountOwnerDto;
import nl.minor.week3.RestAPI.Models.ModelMappers.AccountOwnerMapper;
import nl.minor.week3.RestAPI.Models.ModelMappers.AccountMapper;
import nl.minor.week3.RestAPI.Repos.AccountRepository;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("accounts")
public class AccountController {

    private final AccountRepository _accountRepository;
    private final AccountMapper _accountMapper;
    private final AccountOwnerMapper _accountOwnerMapper;

    public AccountController(
            AccountMapper accountMapper,
            AccountOwnerMapper accountOwnerMapper,
            AccountRepository accountRepository) {

        _accountRepository = accountRepository;
        _accountMapper = accountMapper;
        _accountOwnerMapper = accountOwnerMapper;
    }

    @GetMapping(value = "", produces = "application/json")
    @ApiOperation("Returns a list of accounts")
    public ResponseEntity<Collection<AccountDto>> getAllAccounts(
            @RequestParam Integer offset,
            @RequestParam Integer limit
    ) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .body(_accountRepository.GetAccounts(offset, limit).stream().map(_accountMapper::ToDto).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{accountId}", produces = "application/json")
    @ApiOperation("Get account by ID")
    public ResponseEntity<AccountDto> getAccount(
            @PathVariable Long accountId
    ) {
        Optional<Account> account = _accountRepository.GetAccount(accountId);

        if (account.isPresent()) {
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(_accountMapper.ToDto(account.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{firstName}/{surname}", produces = "application/json")
    @ApiOperation("Get account details by surname and firstname")
    public ResponseEntity<Collection<AccountDto>> getAccounts(
            @PathVariable String firstName,
            @PathVariable String surname
    ) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .body(_accountRepository.GetAccounts(firstName, surname).stream().map(_accountMapper::ToDto).collect(Collectors.toList()));
    }

    @PostMapping(value = "", consumes = "application/json")
    @ApiOperation("Post new account")
    public ResponseEntity<?> saveAccount(
            @RequestBody AccountDto account) {
        var accountToSave = _accountMapper.ToDomainModel(account);
        _accountRepository.Save(accountToSave);

        UriComponents uriComponents = UriComponentsBuilder.fromPath("/accounts/{id}").buildAndExpand(accountToSave.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "", consumes = "application/json")
    @ApiOperation("Delete account by ID")
    public ResponseEntity deleteAccount(
            @RequestBody AccountDto account
    ) {
        var existingAccount = _accountRepository.GetAccount(account.getId());
        if (!existingAccount.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        var accountToSave = _accountMapper.ToDomainModel(account);
        _accountRepository.Delete(accountToSave);

        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/actions/block/{accountId}")
    @ApiOperation("Blocks account by id")
    public ResponseEntity<?> blockAccount(
            @PathVariable Long accountId
    ) {
        var existingAccount = _accountRepository.GetAccount(accountId);
        if (!existingAccount.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        _accountRepository.BlockAccount(accountId);

        UriComponents uriComponents = UriComponentsBuilder.fromPath("/accounts/{id}").buildAndExpand(accountId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @PatchMapping(value = "/accountowner/add", consumes = "application/json")
    @ApiOperation("Add account owner")
    public ResponseEntity<?> addAccountHolder(
            @PathVariable Long accountId,
            @RequestBody AccountOwnerDto accountOwner
    ) {
        var existingAccount = _accountRepository.GetAccount(accountId);
        if (!existingAccount.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        var accountHolderDomainModel = _accountOwnerMapper.ToDomainModel(accountOwner);
        _accountRepository.AddAccountOwner(accountId, accountHolderDomainModel);

        UriComponents uriComponents = UriComponentsBuilder.fromPath("/accounts/{id}").buildAndExpand(accountId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @PatchMapping(value = "/accountholder/remove", consumes = "application/json")
    @ApiOperation("Removes an account holder from given account")
    public ResponseEntity<?> removeAccountHolder(
            @PathVariable Long accountId,
            @RequestBody AccountOwnerDto accountHolder
    ) {
        var existingAccount = _accountRepository.GetAccount(accountId);
        if (!existingAccount.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        var accountHolderDomainModel = _accountOwnerMapper.ToDomainModel(accountHolder);
        _accountRepository.RemoveAccountOwner(accountId, accountHolderDomainModel);

        UriComponents uriComponents = UriComponentsBuilder.fromPath("/accounts/{id}").buildAndExpand(accountId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
}
