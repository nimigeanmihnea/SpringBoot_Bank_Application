package app.dl;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by MIHONE on 3/26/2017.
 */

@Entity
public class Operation {

    @Id
    @Column(name = "operation_time")
    private Date operationTime;

    @Column(name = "operation_name")
    private String operationName;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Operation(){}

    public Operation(Date operationTime, String operationName, Client client, User user){
        this.operationTime = operationTime;
        this.operationName = operationName;
        this.client = client;
        this.user = user;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public String getOperationName() {
        return operationName;
    }

    public User getUser() {
        return user;
    }

    public Client getClient() {
        return client;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public void setOperationTime(Timestamp operationTime) {
        this.operationTime = operationTime;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
