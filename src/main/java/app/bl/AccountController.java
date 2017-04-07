package app.bl;

import app.dl.Account;
import app.dl.Client;
import app.dl.Operation;
import app.dl.User;
import app.repository.AccountRepository;
import app.repository.ClientRepository;
import app.repository.OperationRepository;
import app.repository.UserRepository;
import app.validation.AccountValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by MIHONE on 3/26/2017.
 */

@Controller
@RequestMapping(value = "/home")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/{id}/view_account", method = RequestMethod.GET)
    public String viewAccount(@PathVariable long id, Model model){
        Account account = accountRepository.findOne(id);
        model.addAttribute("account",account);
        return "/view_account";
    }

    @RequestMapping(value = "home/add_account", method = RequestMethod.POST)
    public String addNewAccount(HttpServletRequest request){
            List<Account> accountList = accountRepository.findAll();
            Account account = new Account(request.getParameter("accountType"), Float.parseFloat(request.getParameter("accountBalance")), new Date());
            AccountValidation validation = new AccountValidation();

            Client client = clientRepository.findByClientCnp(request.getParameter("clientCnp"));

            if (client != null) {
                account.setClient(client);
                if (!accountList.contains(account) && validation.validateAccount(account)) {
                    accountRepository.save(account);
                }
                else
                    return "redirect:/errorpage";
            }
            else
                return "redirect:/errorpage";

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userRepository.findByEmployeeUsername(username);
            Operation operation = new Operation(new Date(),"Added account "+account.getAccountId(),client,user);
            operationRepository.save(operation);

            return "redirect:/home";
    }

    @RequestMapping(value = "/update_account", method = RequestMethod.GET)
    public String showUpdate(){return "/update_account";}

    @RequestMapping(value = "/{id}/update_account", method = RequestMethod.GET)
    public String viewUpdate(@PathVariable long id, Model model){
        Account account = accountRepository.findOne(id);
        model.addAttribute("account",account);
        return "/home/update_account";
    }
    @RequestMapping(value = "updateaccount", method = RequestMethod.POST)
    public String updateAccount(HttpServletRequest request){
        Account account = accountRepository.findOne(Long.parseLong(request.getParameter("search")));

        if(account != null) {
            account.setAccountType(request.getParameter("accountType"));
            account.setAccountBalance(Float.parseFloat(request.getParameter("accountBalance")));

            accountRepository.save(account);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userRepository.findByEmployeeUsername(username);
            Operation operation = new Operation(new Date(),"Account with ID: "+account.getAccountId()+" was updated.", account.getClient(),user);
            operationRepository.save(operation);

            return "redirect:/home";
        }
        else
            return "redirect:/errorpage";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteAccount(@PathVariable long id) {
        Account account = accountRepository.findOne(id);

        if(account != null){
            accountRepository.delete(id);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userRepository.findByEmployeeUsername(username);
            Operation operation = new Operation(new Date(),"Account with ID: "+id+" was deleted.", account.getClient(),user);
            operationRepository.save(operation);
            return "redirect:/home";
        }
        else
            return "redirect:/errorpage";
    }
}
