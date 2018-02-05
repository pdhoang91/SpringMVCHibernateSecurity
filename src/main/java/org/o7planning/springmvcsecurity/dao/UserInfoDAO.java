package org.o7planning.springmvcsecurity.dao;
 
import java.util.List;
 
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.o7planning.springmvcsecurity.entity.User;
import org.o7planning.springmvcsecurity.entity.UserRole;
import org.o7planning.springmvcsecurity.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
@Transactional
public class UserInfoDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    public UserInfoDAO() {
 
    }
 
    public UserInfo findUserInfo(String userName) {
        String sql = "Select new " + UserInfo.class.getName() + "(u.username,u.password) "//
                + " from " + User.class.getName() + " u where u.username = :username ";
 
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("username", userName);
         
        return (UserInfo) query.uniqueResult();
    }
 
    public List<String> getUserRoles(String userName) {
        String sql = "Select r.userRole "//
                + " from " + UserRole.class.getName() + " r where r.user.username = :username ";
 
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("username", userName);
         
        List<String> roles = query.list();
 
        return roles;
    }
 
}