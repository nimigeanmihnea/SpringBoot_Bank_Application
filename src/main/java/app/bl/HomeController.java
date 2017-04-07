package app.bl;

import app.dl.Account;
import app.dl.Client;
import app.dl.Employee;
import app.repository.AccountRepository;
import app.repository.ClientRepository;
import app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by MIHONE on 4/6/2017.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @RequestMapping(method = RequestMethod.POST)
    public String search(HttpServletRequest request) {
        String selectedOperation = request.getParameter("option");

        if(selectedOperation.equals("viewAccount")) {
            Account account = accountRepository.findOne(Long.parseLong(request.getParameter("search")));
            if (account!=null)
                return "redirect:/home/" + account.getAccountId() + "/view_account";
            else
                return "redirect:/errorpage";
        }

        if(selectedOperation.equals("editAccount")){
            Account account = accountRepository.findOne(Long.parseLong(request.getParameter("search")));
            if(account!=null)
                return "redirect:/home/" + account.getAccountId() + "/update_account";
            else
                return "redirect:/errorpage";
        }

        if(selectedOperation.equals("deleteAccount")) {
            Account account = accountRepository.findOne(Long.parseLong(request.getParameter("search")));
            if (account!=null)
                return "redirect:/home/" + account.getAccountId() + "/delete";
            else
                return "redirect:/errorpage";
        }

        if(selectedOperation.equals("viewClient")) {
            Client client = clientRepository.findOne(Integer.parseInt(request.getParameter("search")));
            if(client!=null)
                return "redirect:/home/" + client.getClientId() + "/view";
            else
                return "redirect:/errorpage";
        }

        if(selectedOperation.equals("editClient")){
            Client client = clientRepository.findOne(Integer.parseInt(request.getParameter("search")));
            if (client!=null)
                return "redirect:/home/" + client.getClientId() + "/update_client";
            else
                return "redirect:/errorpage";
        }
        return "redirect:/home";
    }
}
