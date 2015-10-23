package model.dao;

import java.util.HashSet;
import java.util.Set;

import model.dto.BookingDTO;
import model.dto.HotelDTO;
import model.dto.ManagerDTO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class ManagerDAO {
    	private SessionFactory factory;
	
	public ManagerDAO(){
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public ManagerDTO getManger(String username) {
	    Session session = factory.openSession();
	    Transaction tx = null;
	    ManagerDTO manager = null;
	    try {
		tx = session.beginTransaction();
		Criteria cr = session.createCriteria(ManagerDTO.class);
		cr.add(Restrictions.eq("username", username));
		manager = (ManagerDTO) cr.uniqueResult();
		tx.commit();
	    } catch(HibernateException e){
		if(tx != null)
		    tx.rollback();
		e.printStackTrace();
	    } finally{
		session.close();
	    }
	    return manager;
	}
}
