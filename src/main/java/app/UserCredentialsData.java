package app;

import app.dl.User;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by MIHONE on 3/27/2017.
 */

@Service
public class UserCredentialsData implements UserDetailsService{

    private final UserRepository userRepository;

    @Autowired
    public UserCredentialsData(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String employeeUsername) throws UsernameNotFoundException{
        User user = this.userRepository.findByEmployeeUsername(employeeUsername);
        user.isEnabled();
        return new User(user.getUsername(), user.getPassword(), user.getRole());

    }
}
