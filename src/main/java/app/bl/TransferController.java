package app.bl;

import app.dl.Account;
import app.dl.Operation;
import app.dl.User;
import app.repository.AccountRepository;
import app.repository.OperationRepository;
import app.repository.UserRepository;
import app.validation.TransferValidation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created by MIHONE on 4/6/2017.
 */

@Controller
@RequestMapping(value = "/transfer")
public class TransferController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OperationRepository operationRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String show(){return "/transfer";}

    @RequestMapping(method = RequestMethod.POST)
    public String transfer(HttpServletRequest request){
        Account from = accountRepository.getOne(Long.parseLong(request.getParameter("first")));
        Account to = accountRepository.getOne(Long.parseLong(request.getParameter("second")));
        float sum = Float.parseFloat(request.getParameter("sum"));

        TransferValidation validation = new TransferValidation();
        if(validation.validateTransfer(from,to,sum)){
            from.setAccountBalance(from.getAccountBalance() - sum);
            to.setAccountBalance(to.getAccountBalance() + sum);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userRepository.findByEmployeeUsername(username);
            Operation operation = new Operation(new Date(), "Transfer from " + from.getAccountId() + " to " + to.getAccountId(), null, user);

            accountRepository.save(from);
            accountRepository.save(to);
            operationRepository.save(operation);

            return "redirect:/home";
        }
        else
            return "redirect:/errorpage";
    }
}
