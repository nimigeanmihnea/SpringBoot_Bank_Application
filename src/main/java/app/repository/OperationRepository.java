package app.repository;

import app.dl.Operation;
import app.dl.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by MIHONE on 3/26/2017.
 */

@Repository
public interface OperationRepository extends JpaRepository<Operation, Timestamp> {
    Operation findByOperationName(String operationName);
    List<Operation> findByUser(User user);
}
