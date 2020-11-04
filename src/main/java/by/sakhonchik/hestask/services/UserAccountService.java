package by.sakhonchik.hestask.services;

import by.sakhonchik.hestask.dto.UserAccountDto;
import by.sakhonchik.hestask.entities.Role;
import by.sakhonchik.hestask.entities.Status;
import by.sakhonchik.hestask.entities.UserAccount;
import by.sakhonchik.hestask.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

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

    @PostConstruct
    public void initFirstUser(){
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("admin");
        userAccount.setPassword(passwordEncoder.encode("12zxasqW!"));
        userAccount.setFirstName("Dmitry");
        userAccount.setLastName("Sakhonchik");
        userAccount.setRole(Role.ADMIN);
        userAccount.setStatus(Status.ACTIVE);
        userAccount.setCreateAt(LocalDate.now());
        userAccountRepository.save(userAccount);
    }




}
