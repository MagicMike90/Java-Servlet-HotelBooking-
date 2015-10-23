package model.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.bytecode.Descriptor.Iterator;
import model.dto.BookingDTO;
import model.dto.DiscountDTO;
import model.dto.HotelDTO;
import model.dto.HotelRoomDTO;
import model.dto.PeakPeriodDTO;
import model.dto.RoomTypeDTO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class RoomDAO {
	private SessionFactory factory;
	
	public RoomDAO(){
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	
	
	public Map<RoomTypeDTO,Long> findRoomCapcaities(int hotelId, double maxPrice, Calendar checkIn, Calendar checkOut){
		Session session = factory.openSession();
		Transaction tx = null;
		Map<RoomTypeDTO,Long> roomCapacities = new HashMap<RoomTypeDTO,Long>();
		long capacity = -1;
		try{
			tx = session.beginTransaction();
			List<RoomTypeDTO> roomTypes = findSuitableRoomTypes(session, hotelId, maxPrice, checkIn, checkOut);
			for(RoomTypeDTO rt : roomTypes){
				Criteria cr = session.createCriteria(HotelRoomDTO.class);
				cr.add(Restrictions.eq("roomType",rt));
				cr.add(Restrictions.eq("hotel.id", hotelId));
				cr.setProjection(Projections.rowCount());
				capacity = (Long)cr.uniqueResult();
				roomCapacities.put(rt, capacity);
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally{
			session.close();
		}
		return roomCapacities;
	}
	

	
	private Integer findPremium(Session session, Calendar checkIn, Calendar checkOut){
		Criteria cr = session.createCriteria(PeakPeriodDTO.class);	
		cr.add(Restrictions.le("startDate", checkOut));
		cr.add(Restrictions.ge("endDate", checkIn));
		PeakPeriodDTO peakPeriod= (PeakPeriodDTO)cr.uniqueResult();
		return peakPeriod != null ? peakPeriod.getPremiumPercentage() : 0;
	}
	
	private Map<Integer,Integer> findDiscounts(Session session, int hotelId, Calendar checkIn, Calendar checkOut){
		Criteria cr = session.createCriteria(DiscountDTO.class);
		cr.add(Restrictions.eq("hotel.id", hotelId));
		cr.add(Restrictions.le("startDate", checkOut));
		cr.add(Restrictions.ge("endDate", checkIn));
		List<DiscountDTO> discounts = cr.list();
		Map<Integer,Integer> roomDiscounts = new HashMap<Integer,Integer>();
		for(DiscountDTO d : discounts){
			roomDiscounts.put(d.getRoomType().getId(), d.getDiscountPercentage());
		}
		return roomDiscounts;		
	}
	
	private List<RoomTypeDTO> findAllRoomTypes(Session session){
		Criteria cr = session.createCriteria(RoomTypeDTO.class);
		return cr.list();
	}
	
	@SuppressWarnings("unchecked")
	private List<RoomTypeDTO> findSuitableRoomTypes(Session session, int hotelId, double maxPrice, Calendar checkIn, Calendar checkOut){
		Integer premium = findPremium(session, checkIn, checkOut);
		Map<Integer,Integer> roomDiscounts = findDiscounts(session, hotelId, checkIn, checkOut);
		List<RoomTypeDTO> roomTypes = findAllRoomTypes(session);
		List<RoomTypeDTO> suitable = new ArrayList<RoomTypeDTO>();
		Integer discount;
		double correctPrice;
		for(RoomTypeDTO rt : roomTypes){
			discount = roomDiscounts.get(rt.getId());
			if(discount==null) discount = 0;
			correctPrice = rt.getPrice() * (1+premium/100.0) * (1-discount/100.0);
			if(correctPrice <= maxPrice) suitable.add(rt);
		}
		return suitable;
	}
}
