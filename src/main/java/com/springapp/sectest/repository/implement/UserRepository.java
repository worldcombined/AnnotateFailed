package com.springapp.sectest.repository.implement;

import com.springapp.sectest.domain.User;
import com.springapp.sectest.repository.UserDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public User getUser(String login) {
        List<User> userList = new ArrayList<User>();

        Query query = getCurrentSession().createQuery("from User u where u.login = :login");
        query.setParameter("login",login);
        userList = query.list();
        if(userList.size() > 0)
            return userList.get(0);
        else
            return null;
    }
}
