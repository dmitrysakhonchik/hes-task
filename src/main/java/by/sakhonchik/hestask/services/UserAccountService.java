package by.sakhonchik.hestask.services;

import by.sakhonchik.hestask.entities.UserAccount;
import by.sakhonchik.hestask.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {
    private UserAccountRepository userAccountRepository;

    @Autowired
    public void setUserAccountRepository(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccount getUserAccountById(Long id) {
        return userAccountRepository.getOne(id);
    }

    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    public void deleteUserAccountById(Long id){
        userAccountRepository.deleteById(id);
    }

}
