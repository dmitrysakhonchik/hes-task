package by.sakhonchik.hestask.repositories;

import by.sakhonchik.hestask.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
