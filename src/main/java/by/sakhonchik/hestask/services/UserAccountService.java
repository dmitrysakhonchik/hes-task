package by.sakhonchik.hestask.services;

import by.sakhonchik.hestask.dto.UserAccountDto;
import by.sakhonchik.hestask.entities.UserAccount;
import by.sakhonchik.hestask.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {
    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(PasswordEncoder passwordEncoder, UserAccountRepository userAccountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccountDto getUserAccountById(Long id) {
        UserAccount userAccountFromDB = userAccountRepository.getOne(id);
        UserAccountDto userAccountDto = new UserAccountDto();

        userAccountDto.setId(userAccountFromDB.getId());
        userAccountDto.setUsername(userAccountFromDB.getUsername());
        userAccountDto.setFirstName(userAccountFromDB.getFirstName());
        userAccountDto.setLastName(userAccountFromDB.getLastName());
        userAccountDto.setRole(userAccountFromDB.getRole());
        userAccountDto.setStatus(userAccountFromDB.getStatus());

        return userAccountDto;
    }

    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    public void deleteUserAccountById(Long id) {
        userAccountRepository.deleteById(id);
    }

    public void addUserAccount(UserAccountDto userAccountDto) {
        UserAccount userAccount = new UserAccount();

        userAccount.setUsername(userAccountDto.getUsername());
        userAccount.setPassword(passwordEncoder.encode(userAccountDto.getPassword()));
        userAccount.setFirstName(userAccountDto.getFirstName());
        userAccount.setLastName(userAccountDto.getLastName());
        userAccount.setRole(userAccountDto.getRole());
        userAccount.setStatus(userAccountDto.getStatus());
        userAccount.setCreateAt(LocalDate.now());
        userAccountRepository.save(userAccount);
    }

    public void updateUserAccount(UserAccountDto userAccountDto) {
        UserAccount userAccount = new UserAccount();

        userAccount.setId(userAccountDto.getId());
        userAccount.setUsername(userAccountDto.getUsername());
        userAccount.setPassword(passwordEncoder.encode(userAccountDto.getPassword()));
        userAccount.setFirstName(userAccountDto.getFirstName());
        userAccount.setLastName(userAccountDto.getLastName());
        userAccount.setRole(userAccountDto.getRole());
        userAccount.setStatus(userAccountDto.getStatus());
        userAccount.setCreateAt(LocalDate.now());
        userAccountRepository.save(userAccount);
    }


    public boolean isUsernameExist(String username) {
        return userAccountRepository.existsUserAccountByUsername(username);
    }




}
