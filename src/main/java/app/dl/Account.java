package app.dl;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by MIHONE on 3/26/2017.
 */

@Entity
public class Account {

    private static final float MAX_LIMIT = 50000;
    private static final float MIN_LIMIT = 0;

    @Id
    @Column(name = "account_id", nullable = false)
    @GeneratedValue
    private long accountId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_balance")
    private float accountBalance;

    @Column(name = "account_creation")
    private Date accountCreation;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Account(){}

    public Account(String accountType, float accountBalance, Date accountCreation){
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.accountCreation = accountCreation;
    }

    public static float getMaxLimit() {
        return MAX_LIMIT;
    }

    public static float getMinLimit() {
        return MIN_LIMIT;
    }

    public long getAccountId() {
        return accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public Date getAccountCreation() {
        return accountCreation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setAccountCreation(Timestamp accountCreation) {
        this.accountCreation = accountCreation;
    }

    @Override
    public String toString(){
        return ("ID: "+accountId+"\nHolder: "+client.toString());
    }
}
