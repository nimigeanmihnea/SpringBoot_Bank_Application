package app.dl;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIHONE on 3/19/2017.
 */

@Entity
public class Client {

    @Id
    @Column(name = "client_id", nullable = false)
    @GeneratedValue
    private int clientId;

    @Column(name = "client_cnp", nullable = false, unique = true)
    private String clientCnp;

    @Column(name = "client_id_number", unique = true)
    private String clientIdNumber;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_email", unique = true)
    private String clientEmail;

    @Column(name = "client_phone", unique = true)
    private String clientPhone;

    @Column(name = "client_address")
    private String clientAddress;

    public Client(){}

    public Client(String clientCnp, String clientIdNumber, String clientName, String clientEmail, String clientPhone, String clientAddress){
        this.clientCnp = clientCnp;
        this.clientIdNumber = clientIdNumber;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
        this.clientAddress = clientAddress;
    }

    public int getClientId(){ return this.clientId;}
    public String getClientCnp(){ return this.clientCnp;}
    public String getClientIdNumber(){return this.clientIdNumber;}
    public String getClientName(){ return this.clientName;}
    public String getClientEmail(){ return this.clientEmail;}
    public String getClientPhone(){ return  this.clientPhone;}
    public String getClientAddress(){ return  this.clientAddress;}

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setClientCnp(String clientCnp) {
        this.clientCnp = clientCnp;
    }

    public void setClientIdNumber(String clientIdNumber) {
        this.clientIdNumber = clientIdNumber;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    @Override
    public String toString(){
        return ("Client ID: "+this.clientId+"\nClient name: "+this.clientName+"\n CNP: "+this.clientCnp+"\t CI: "
                +this.clientIdNumber+"\nEmail: "+this.clientEmail+"\tPhone: "
                +this.clientPhone+"\nAddress: "+this.clientAddress+"\n");
    }


}
