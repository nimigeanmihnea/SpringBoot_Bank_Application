package app.validation;

import app.dl.Account;

/**
 * Created by MIHONE on 4/6/2017.
 */
public class WithdrawValidation {

    public boolean validateWithdraw(Account from, float sum){
        return (from.getAccountBalance() - sum >= from.getMinLimit()) && sum > 0;
    }
}
