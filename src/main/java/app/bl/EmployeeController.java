package app.bl;

import app.dl.Employee;
import app.dl.Operation;
import app.dl.User;
import app.repository.EmployeeRepository;
import app.repository.OperationRepository;
import app.repository.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

/**
 * Created by MIHONE on 4/6/2017.
 */

@Controller
@RequestMapping(value = "/admin")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OperationRepository operationRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String show(){return "/admin";}

    @RequestMapping(value = "/{id}/view_employee", method = RequestMethod.GET)
    public String viewEmployee(@PathVariable int id, Model model){
        Employee employee = employeeRepository.findOne(id);
        model.addAttribute("employee",employee);
        return "/view_employee";
    }

    @RequestMapping(value = "/add_employee", method = RequestMethod.GET)
    public String employee(){return "/add_employee";}

    @RequestMapping(value = "/add_employee", method = RequestMethod.POST)
    public String addEmployee(HttpServletRequest request) {
        Employee employee = new Employee(request.getParameter("employeeCnp"), request.getParameter("employeeName"),
                request.getParameter("employeeEmail"), request.getParameter("employeePhone"), request.getParameter("employeeAddress"));
        employeeRepository.save(employee);
        User user = new User(request.getParameter("employeeUsername"), request.getParameter("employeePassword"), "ROLE_USER");
        userRepository.save(user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User currentUser = userRepository.findByEmployeeUsername(username);
        Operation operation = new Operation(new Date(), "Added new employee with ID: " + employee.getEmployeeId(), null, currentUser);
        operationRepository.save(operation);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/update_employee", method = RequestMethod.GET)
    public String showUpdate(){return "/update_employee";}

    @RequestMapping(value = "/{id}/update_employee", method = RequestMethod.GET)
    public String viewUpdate(@PathVariable int id, Model model){
        Employee employee = employeeRepository.findOne(id);
        model.addAttribute(employee);
        return "/admin/update_employee";
    }

    @RequestMapping(value = "updateemployee", method = RequestMethod.POST)
    public String updateEmployee(HttpServletRequest request){
        Employee employee = employeeRepository.findOne(Integer.parseInt(request.getParameter("search")));

        if(employee!=null){
            employee.setEmployeeCnp(request.getParameter("employeeCnp"));
            employee.setEmployeeName(request.getParameter("employeeName"));
            employee.setEmployeeEmail(request.getParameter("employeeEmail"));
            employee.setEmployeePhone(request.getParameter("employeePhone"));
            employee.setEmployeeAddress(request.getParameter("employeeAddress"));

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userRepository.findByEmployeeUsername(username);
            Operation operation = new Operation(new Date(), "Added new employee with ID: " + employee.getEmployeeId(), null, user);

            employeeRepository.save(employee);
            operationRepository.save(operation);

            return "redirect:/admin";
        }
        else
            return "redirect:/admin/update_employee?param=error";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeRepository.findOne(id);
        if(employee != null){
            employeeRepository.delete(id);
            userRepository.delete(id+1);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User currentUser = userRepository.findByEmployeeUsername(username);
            Operation operation = new Operation(new Date(),"Employee with ID: "+id+" was deleted.",null,currentUser);
            operationRepository.save(operation);
            return "redirect:/admin";
        }
        else
            return "redirect:/admin?param=error";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String showReport(){return "admin/report";}

    @RequestMapping( value = "/report", method = RequestMethod.POST)
    public ModelAndView reportSearch(HttpServletRequest request){
        ModelAndView model = new ModelAndView("admin/show_report");
        User user = userRepository.findOne(Integer.parseInt(request.getParameter("userID")));
        List<Operation> report = operationRepository.findByUser(user);
        model.addObject("report",report);
        return model;
    }

    @RequestMapping(value = "/show_report",method = RequestMethod.GET)
    public String viewReport() {
        return "admin/show_report";
    }

}
