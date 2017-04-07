package app.dl;

import javax.persistence.*;

/**
 * Created by MIHONE on 3/24/2017.
 */

@Entity
public class Employee {

    @Id
    @Column(name = "employee_id", nullable = false)
    @GeneratedValue
    private int employeeId;

    @Column(name = "employee_cnp", nullable = false, unique = true)
    private String employeeCnp;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_email", unique = true)
    private String employeeEmail;

    @Column(name = "employee_phone", unique = true)
    private String employeePhone;

    @Column(name = "employee_address")
    private String employeeAddress;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private User user;

    public Employee(){}

    public Employee(String employeeCnp, String employeeName, String employeeEmail, String employeePhone, String employeeAddress){
        this.employeeCnp = employeeCnp;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeePhone = employeePhone;
        this.employeeAddress = employeeAddress;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeCnp() {
        return employeeCnp;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public User getUser(){ return user;}

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeCnp(String employeeCnp) {
        this.employeeCnp = employeeCnp;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

}
