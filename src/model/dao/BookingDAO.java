package model.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.dto.BookingDTO;
import model.dto.HotelDTO;
import model.dto.RoomBookingDTO;
import model.dto.RoomTypeDTO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class BookingDAO {
	private SessionFactory factory;
	
	public BookingDAO(){
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public BookingDTO findBooking(String url) {
	    Session session = factory.openSession();
	    Transaction tx = null;
	    BookingDTO booking = null;
	    try {
		tx = session.beginTransaction();
		String hq = "SELECT B FROM BookingDTO B where B.url = :url";
		Query query = session.createQuery(hq);
		query.setParameter("url", url);
		booking = (BookingDTO)query.uniqueResult();
		tx.commit();
	    } catch(HibernateException e){
		if(tx != null)
		    tx.rollback();
		e.printStackTrace();
	    } finally{
		session.close();
	    }
	    return booking;
	}
	
	@SuppressWarnings("deprecation")
	public Map<RoomTypeDTO,List<BookingDTO>> findBookingsOfRoom(int hotelId, Set<RoomTypeDTO> set){
		Session session = factory.openSession();
		Transaction tx = null;
		Map<RoomTypeDTO,List<BookingDTO>> bookingsOfRoom = new HashMap<RoomTypeDTO,List<BookingDTO>>();
		List<BookingDTO> bookings = new ArrayList<BookingDTO>();
		try{
			tx = session.beginTransaction();
			for(RoomTypeDTO rt : set){
				Criteria cr = session.createCriteria(BookingDTO.class, "b");
				cr.createAlias("b.roomBookings", "rb");
				cr.add(Restrictions.eq("rb.roomType", rt));
				cr.add(Restrictions.eq("b.hotel.id", hotelId));
				bookings = cr.list();
				bookingsOfRoom.put(rt, bookings);
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
		return bookingsOfRoom;
	}
	
	public void loadHotelToBooking(BookingDTO booking, int hotelId){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			HotelDTO hotel = (HotelDTO) session.get(HotelDTO.class, hotelId);
			booking.setHotel(hotel);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void loadRoomTypeToBooking(RoomBookingDTO roomBooking, int roomTypeId){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			RoomTypeDTO roomType = (RoomTypeDTO) session.get(RoomTypeDTO.class, roomTypeId);
			roomBooking.setRoomType(roomType);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void saveBooking(BookingDTO booking){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.persist(booking);
			tx.commit();
		} catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
	}
	
	public void deleteBooking(BookingDTO booking){
	    Session session = factory.openSession();
	    Transaction tx = null;
	    try{
		tx = session.beginTransaction();
		session.delete(booking);
		tx.commit();
	    } catch(HibernateException e){
		if(tx!=null)
			tx.rollback();
		e.printStackTrace();
	    } finally{
		session.close();
	    }
	}
	
	public void updateBooking(BookingDTO booking){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.saveOrUpdate(booking);
			tx.commit();
		} catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
	}
}
