package CRUD.dao;

import CRUD.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addRole(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }

    /*public void checkRolesEmpty(){
        Session session = sessionFactory.getCurrentSession();
        if(session.createQuery("FROM Role").list().isEmpty()){
            session.save(new Role("ROLE_user"));
            session.save(new Role("ROLE_admin"));
        }
    }*/

    public void checkRoleEmpty(String role){
        Session session = sessionFactory.getCurrentSession();
        if (session.createQuery("FROM Role WHERE role = :role").setParameter("role", "ROLE_" + role).list().isEmpty()){
            session.save(new Role("ROLE_" + role));
        }
    }

    public Role getById(Long id) {
        return sessionFactory.getCurrentSession().get(Role.class, id);
    }

    public Role getByRole(String role){
        return (Role) sessionFactory.getCurrentSession().createQuery("FROM Role WHERE role = :role").setParameter("role", "ROLE_" + role).getSingleResult();
    }

    public int updateRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        Query<Role> query = session.createQuery("UPDATE Role SET role = :role WHERE id = :id");
        query.setParameter("role", role.getRole());
        query.setParameter("id", role.getId());
        query.executeUpdate();
        return 0;
    }

    public int countRoles(String roleName) {
        int result = 0;
        Session session = sessionFactory.getCurrentSession();
        Role role = (Role) session.createQuery("FROM Role WHERE role = :role").setParameter("role", roleName).getSingleResult();
        result = role.getUsers().size();
        return result;
    }

    public void deleteRole(Role role){
        sessionFactory.getCurrentSession().delete(role);
    }

    public void dropTable() {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("DELETE FROM Role").executeUpdate();
    }
}
