package CRUD.service;

import CRUD.dao.UserDAO;
import CRUD.model.Role;
import CRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service
@Transactional(rollbackFor = SQLException.class)
public class UserService implements UserDetailsService {

    private UserDAO dao;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean addUser(User user){
        if (doesUserNotExist(user.getUsername())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            dao.addUser(user);
            return true;
        } else return false;
    }

    public boolean doesUserNotExist(String name){
        return dao.doesUserNotExist(name);
    }

    public List<User> getAllUsers() {
        return (List) dao.getAllUsers();
    }

    public void updateUser(User user){
        if(!user.getPassword().equals("")) user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        dao.updateUser(user);
    }

    public User getById(Long id){
        return dao.getById(id);
    }

    public User getByName(String name) {
        return dao.getByName(name);
    }

    public void deleteUser(Long id){
        dao.deleteUser(id);
    }

    public void dropTable(){
        dao.dropTable();
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = dao.getByName(name);
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        for(Role role : user.getRoles()){
            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthoritySet);
    }
}
