package CRUD.dao;

import CRUD.model.Role;
import CRUD.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public class UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean doesUserNotExist(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User WHERE name = :name");
        query.setParameter("name", name);
        return query.list().isEmpty();
    }

    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public User getById(Long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    public User getByName(String name) {
        User user = null;
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User WHERE name = :name");
        query.setParameter("name", name);
        if(!query.list().isEmpty()) user = query.list().get(0);
        return user;
    }

    public void deleteUser(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = getById(id);
        Set<Role> roles = user.getRoles();
        for(Role role : roles){
            session.delete(role);
        }
        session.delete(user);
    }

    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        User existingUser = session.get(User.class, user.getId());
        existingUser.setName(user.getUsername());
        if(!user.getPassword().equals("")) {
            existingUser.setPassword(user.getPassword());
        }
        existingUser.setAge(user.getAge());
        if(user.getRoles() != null) {
            Set<Role> roles = existingUser.getRoles();
            for(Role role : roles) {
                session.delete(role);
            }
            existingUser.setRoles(user.getRoles());
        }
        sessionFactory.getCurrentSession().save(existingUser);
    }

    public Collection getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("FROM User").list();
    }

    public void dropTable() {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.createQuery("DELETE FROM Role").executeUpdate();
    }
}
