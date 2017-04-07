package app.bl;

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
@RequestMapping( value = "/admin")
public class AdminController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @RequestMapping(method = RequestMethod.POST)
    public String search(HttpServletRequest request) {
        String selectedOperation = request.getParameter("option");

        if(selectedOperation.equals("viewEmployee")){
            Employee employee = employeeRepository.findOne(Integer.parseInt(request.getParameter("search")));
            if (employee!=null)
                return "redirect:/admin/" + employee.getEmployeeId() + "/view_employee";
            else
                return "redirect:/errorpage";
        }

        if(selectedOperation.equals("editEmployee")){
            Employee employee = employeeRepository.findOne(Integer.parseInt(request.getParameter("search")));
            if (employee!=null)
                return "redirect:/admin/" + employee.getEmployeeId() + "/update_employee";
            else
                return "redirect:/errorpage";
        }

        if(selectedOperation.equals("deleteEmployee")){
            Employee employee = employeeRepository.findOne(Integer.parseInt(request.getParameter("search")));
            if(employee!=null)
                return "redirect:/admin/" + employee.getEmployeeId() + "/delete";
            else
                return "redirect:/errorpage";
        }

        return "redirect:/admin";
    }
}
