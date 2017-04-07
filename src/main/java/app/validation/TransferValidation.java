package app.validation;

import app.dl.Account;

/**
 * Created by MIHONE on 4/6/2017.
 */
public class TransferValidation {

    public boolean validateTransfer(Account from, Account to, float sum){
        return ((from.getAccountBalance() - sum) >= from.getMinLimit()) && ((to.getAccountBalance() + sum) <= to.getMaxLimit()) && sum > 0;
    }
}
