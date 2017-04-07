package app.validation;

import app.dl.Account;

/**
 * Created by MIHONE on 4/5/2017.
 */
public class AccountValidation {
    public boolean validateAccount(Account account){
        return (validateAccountType(account.getAccountType()) && validateAccountBalance(account.getAccountBalance()));
    }

    private boolean validateAccountBalance(float balance){
        return (balance >= Account.getMinLimit() && balance <= Account.getMaxLimit());
    }
    private boolean validateAccountType(String type){
        return type.equalsIgnoreCase("credit") || type.equalsIgnoreCase("debit");
    }
}
