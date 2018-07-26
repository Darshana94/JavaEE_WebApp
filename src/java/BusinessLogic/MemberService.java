/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DTO.Registration;
import DTO.User;
import Model.Members;
import Model.Users;
import Util.NewHibernateUtil;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Chathuranga
 */
public class MemberService {
    
    public List<Members> getAllRecords()
    {
        Session session = null;
        Transaction tx = null;
        List<Members> list = new ArrayList<Members>();
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();

        list = session.createQuery("FROM Members").list();        
        
        tx.commit();
        session.close();
        return list;
    }
        
    public List getAll()
    {
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList<>();
        User userModel = new User();
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();

        Query query = session.createQuery("FROM Members");
        List<Members> queryResult = (List<Members>)query.list();
        
        queryResult.forEach(element -> {
            
            String[] split = element.getName().split(" ");
            
            userModel.setID(element.getId());
            userModel.setAddress(element.getAddress());
            userModel.setBalance(element.getBalance());
            userModel.setDOB(element.getDob().toString());
            userModel.setDOR(element.getDor());
            userModel.setFirstName(split[0]);
            userModel.setLastName(split[1]);
            userModel.setUserValid(element.getStatus());
            
            list.add(userModel);
        });
        
        tx.commit();
        session.close();
        return list;
    }
    
    public User getSingleById(String id){
        User user = new User();
        
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList();
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();

        Query query = session.createQuery("FROM Members where id ='" +id + "'");
        Members queryResult = (Members)query.uniqueResult();
        
        String[] split = queryResult.getName().split(" ");
        
        user.setID(queryResult.getId());
        user.setFirstName(split[0]);
        user.setLastName(split[1]);
        user.setUserValid(queryResult.getStatus());
        user.setAddress(queryResult.getAddress());
        user.setDOB(queryResult.getDob().toString());
        user.setDOR(queryResult.getDor());
        user.setBalance(queryResult.getBalance());
        
        tx.commit();
        session.close();
        
        return user;
    }
    
//    public List getRecordsById(String id)
//    {
//        Session session = null;
//        Transaction tx = null;
//        List list = new ArrayList();
//        
//        session = NewHibernateUtil.getSessionFactory().openSession();
//        tx = session.getTransaction();
//        tx.begin();
//
//        Query query = session.createQuery("FROM Members where id = '" +id+"'");
//        List<Members> queryResult = (List<Members>)query.list();
//        
//        queryResult.forEach(element -> {
//            list.add(element);
//        });
//        
//        tx.commit();
//        session.close();
//        return list;
//    }
//    
//    public List getRecordsByStatus(String status)
//    {
//        Session session = null;
//        Transaction tx = null;
//        List list = new ArrayList();
//        
//        session = NewHibernateUtil.getSessionFactory().openSession();
//        tx = session.getTransaction();
//        tx.begin();
//
//        Query query = session.createQuery("FROM Members where status = '" +status+"'");
//        List<Members> queryResult = (List<Members>)query.list();
//        
//        queryResult.forEach(element -> {
//            list.add(element);
//        });
//        
//        tx.commit();
//        session.close();
//        return list;
//    }
    
    public String getColumn(String id,String column){
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList();
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();
        
        Query query = session.createQuery( "SELECT " + column + " FROM members WHERE id='" + id + "'");       
        Members queryResult = (Members)query.uniqueResult();

        tx.commit();
        session.close();
        
        return queryResult.toString();  
    }
    
    public String editDetails(User user)throws SQLException,IOException{
        
        Session session = null;
        Transaction tx = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();
        
        Members m = (Members) session.get(Members.class, user.getID());
        m.setName(user.getFirstName() + " " + user.getLastName());
        m.setAddress(user.getAddress());
        try {
            m.setDob(format.parse(user.getDOB()));
        } catch (ParseException e) {
        }
        
        
//        Query query = session.createQuery("UPDATE Members SET " + 
//                                            "name='" + user.getFirstName() + " " + user.getLastName() + "'," + 
//                                            "address='"+ user.getAddress() +"'," +
//                                            "dob='"+ user.getDOB() +"'" +
//                                            "WHERE id='"+ user.getID() + "'");
//        
//        Members memModel = (Members)query.uniqueResult();
//        
//        memModel.setName(user.getFirstName() + " " + user.getLastName());
//        memModel.setAddress(user.getAddress());
//        try {
//            memModel.setDob(format.parse(user.getDOB()));
//        } catch (ParseException e) {
//        }
//        
//        session.update(memModel);
        tx.commit();
        session.close();
        
        String result = "User details successfully updated.";        
        return result;
    }
    
    public String deleteUser(String id)throws SQLException{
        
        Session session = null;
        Transaction tx = null;
        
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();
        
        User u = (User) session.load(Users.class, id);
        session.delete(u);
        Members m = (Members) session.get(Members.class, id);
        m.setStatus("DELETE");
        
        tx.commit();
        session.close();
        
        String result = "User " + id + " has been deleted.";        
        return result;
    }
    
 
    public void updateStatus(String user, String status){
        
        Session session = null;
        Transaction tx = null;
        session = NewHibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();
        
        Members memModel = (Members) session.get(Members.class, user);        
        memModel.setStatus(status);
        
        tx.commit();
        session.close();

    }
    
}
