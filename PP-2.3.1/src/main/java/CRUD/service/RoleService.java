package CRUD.service;

import CRUD.dao.RoleDAO;
import CRUD.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional(rollbackFor = SQLException.class)
public class RoleService {

    private RoleDAO dao;

    @Autowired
    public void setDao(RoleDAO dao) {
        this.dao = dao;
    }

    public void addRole(Role role) {
        dao.addRole(role);
    }

    public void checkRoleEmpty(String role){
        dao.checkRoleEmpty(role);
    }

    public Role getById(Long id) {
        return dao.getById(id);
    }

    public Role getByRole(String role){
        checkRoleEmpty(role);
        return dao.getByRole(role);
    }

    public int updateRole(Role role) {
        return dao.updateRole(role);
    }

    public int countRoles(String role){
        return dao.countRoles(role);
    }

    public void deleteRole(Role role){
        dao.deleteRole(role);
    }

    public void dropTable(){
        dao.dropTable();
    }
}
