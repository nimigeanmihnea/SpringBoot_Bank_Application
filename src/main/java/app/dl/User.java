package app.dl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by MIHONE on 3/24/2017.
 */

@Entity
public class User implements UserDetails {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue
    private int employeeId;

    @Column(name = "employee_username", unique = true)
    private String employeeUsername;

    @Column(name = "employee_password")
    private String employeePassword;

    @Column(name = "employee_rights")
    private String role;

    public User(){}

    public User(String employeeUsername, String employeePassword, String employeeRights){
        this.employeeUsername = employeeUsername;
        this.employeePassword = employeePassword;
        this.role = employeeRights;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public String getRole() {
        return role;
    }


    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public void setRole(String employeeRights) {
        this.role = employeeRights;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(this.getRole());
    }

    public String getPassword() {
        return employeePassword;
    }

    public String getUsername() {
        return employeeUsername;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled(){
        return true;
    }

    @Override
    public String toString(){
        return ("ID: "+this.employeeId+" Username: "+this.employeeUsername);
    }


}
