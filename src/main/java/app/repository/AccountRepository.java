package app.repository;

import app.dl.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MIHONE on 3/26/2017.
 */

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{
    Account findByAccountId(String accountId);
}
