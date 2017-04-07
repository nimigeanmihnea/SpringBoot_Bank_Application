package app.bl;

import app.dl.Account;
import app.dl.Operation;
import app.dl.User;
import app.repository.AccountRepository;
import app.repository.OperationRepository;
import app.repository.UserRepository;
import app.validation.WithdrawValidation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by MIHONE on 4/6/2017.
 */

@Controller
@RequestMapping(value = "/withdraw")
public class WithdrawController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String show(){
        return "/withdraw";
    }

    @RequestMapping(method = RequestMethod.POST)
    public  String withdraw(HttpServletRequest request) throws FileNotFoundException, DocumentException {
        Account account = accountRepository.findOne(Long.parseLong(request.getParameter("account")));
        float sum = Float.parseFloat(request.getParameter("sum"));
        String selectedOperation = request.getParameter("option");

        WithdrawValidation validation = new WithdrawValidation();

        if (validation.validateWithdraw(account,sum)){
            account.setAccountBalance(account.getAccountBalance() - sum);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userRepository.findByEmployeeUsername(username);
            Operation operation = new Operation(new Date(), "Withdraw from " + account.getAccountId(), null, user);

            accountRepository.save(account);
            operationRepository.save(operation);

            if(selectedOperation.equals("bill")){
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream("Bill.pdf"));
                doc.open();
                doc.addTitle("Bill");
                doc.addCreationDate();
                doc.add(new Paragraph("Payment: " + sum + "\nFrom account: "+account.toString()));
                doc.close();
            }
            else
            {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream("Withdraw.pdf"));
                doc.open();
                doc.addTitle("Withdraw");
                doc.addCreationDate();
                doc.add(new Paragraph("Withdraw: " + sum + "\nFrom account: "+account.toString()));
                doc.close();
            }

            return "redirect:/home";
        }
        else
            return "redirect:/errorpage";
    }
}
