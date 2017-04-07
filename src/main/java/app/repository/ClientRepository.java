package app.repository;

import app.dl.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MIHONE on 3/26/2017.
 */

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    Client findByClientId(String clientId);
    Client findByClientCnp(String clientCnp);
}
