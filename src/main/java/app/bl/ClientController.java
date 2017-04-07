package app.bl;

import app.dl.Client;
import app.dl.Operation;
import app.dl.User;
import app.repository.ClientRepository;
import app.repository.OperationRepository;
import app.repository.UserRepository;
import app.validation.ClientValidation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.log.UserDataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;

/**
 * Created by MIHONE on 3/26/2017.
 */

@Controller
@RequestMapping("/home")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/{id}/view", method = RequestMethod.GET)
    public String viewClient(@PathVariable int id, Model model){
        Client client = clientRepository.findOne(id);
        model.addAttribute("client",client);
        return "/view";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showHome(){
        return "/home";
    }


    @RequestMapping(value="home/add_client",method = RequestMethod.POST)
    public String addNewClient(HttpServletRequest request){
        Client client = new Client(request.getParameter("clientCnp"),request.getParameter("clientIdNumber"), request.getParameter("clientName")
                ,request.getParameter("clientEmail"),request.getParameter("clientPhone"),request.getParameter("clientAddress"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByEmployeeUsername(username);
        Operation operation = new Operation(new Date(), "Added new client with ID: " + client.getClientId(), client, user);
        clientRepository.save(client);
        operationRepository.save(operation);
        return "redirect:/home";
    }

    @RequestMapping(value="/update_client", method = RequestMethod.GET)
    public String showUpdate(){
        return "/update_client";
    }


    @RequestMapping(value="/{id}/update_client", method = RequestMethod.GET)
    public String viewUpdate(@PathVariable int id, Model model){
        Client client = clientRepository.findOne(id);
        model.addAttribute("client",client);
        return "/home/update_client";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateClient(HttpServletRequest request){
        Client client = clientRepository.findOne(Integer.parseInt(request.getParameter("search")));

        if(client!=null) {
            client.setClientCnp(request.getParameter("clientCnp"));
            client.setClientIdNumber(request.getParameter("clientIdNumber"));
            client.setClientName(request.getParameter("clientName"));
            client.setClientEmail(request.getParameter("clientEmail"));
            client.setClientPhone(request.getParameter("clientPhone"));
            client.setClientAddress(request.getParameter("clientAddress"));
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userRepository.findByEmployeeUsername(username);
            Operation operation = new Operation(new Date(), "Updated client with ID: " + client.getClientId(), client, user);

            clientRepository.save(client);
            operationRepository.save(operation);

            return "redirect:/home";
        }
        else
            return "redirect:home/update_client?param=error";
    }
}
