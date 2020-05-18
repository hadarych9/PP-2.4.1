package CRUD.service;

import CRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService service;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String findLoggedInUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof String) {
            return ((String) principal);
        }
        return null;
    }

    public void autoLogin(String name, String password) {
        User user = service.getByName(name);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(name, password, user.getRoles());
        authenticationManager.authenticate(authToken);
        if(authToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }
}
