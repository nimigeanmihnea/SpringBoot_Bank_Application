package app.validation;

import app.dl.Client;

/**
 * Created by MIHONE on 4/5/2017.
 */
public class ClientValidation {
    public boolean validate(Client client) {
        return validateClientName(client.getClientName()) && validateClientCnp(client.getClientCnp()) && validateClientEmail(client.getClientEmail());
    }

    private boolean validateClientName(String name) {
        return name.matches("[a-zA-Z\\s]");
    }

    private boolean validateClientCnp(String cnp){
        return (cnp.startsWith("1") || cnp.startsWith("2")) && (cnp.length()==13);
    }

    private boolean validateClientEmail(String email) {
        return email.contains("@") && email.contains(".com");
    }
}
