package app.repository;

import app.dl.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MIHONE on 3/26/2017.
 */

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmployeeUsername(String employeeUsername);
}
