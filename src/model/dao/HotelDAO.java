package model.dao;

import java.io.Serializable;

import model.dto.HotelDTO;
import model.dto.RoomTypeDTO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HotelDAO implements Serializable{
	private SessionFactory factory;
	
	public HotelDTO getHotel(int id) {
	    	Session session = factory.openSession();
		Transaction tx = null;
		HotelDTO hotel = null;
		try {
			tx = session.beginTransaction();
			hotel = (HotelDTO) session.get(HotelDTO.class, id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println(hotel.getCity());
		return hotel;
	}
}
