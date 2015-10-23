package controller.commands;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BookingBean;
import beans.SearchResultBean;
import model.dao.BookingDAO;
import model.dao.RoomDAO;
import model.dto.BookingDTO;
import model.dto.RoomTypeDTO;

public class SearchCommand implements Command{
	private BookingDAO bookingDao;
	private RoomDAO roomDao;
	
	public SearchCommand(){
		bookingDao = new BookingDAO();
		roomDao = new RoomDAO();
	}
	
	private Map<RoomTypeDTO,Long> search(HttpServletRequest request){
		String maxPriceString = request.getParameter("maxPrice");
		Double maxPrice;
		if(maxPriceString==null){
		    maxPrice = Double.MAX_VALUE;
		}else if(maxPriceString.equals("")){
		    maxPrice = Double.MAX_VALUE;
		}else
		    maxPrice = Double.parseDouble(maxPriceString);
		
		BookingBean bb = (BookingBean)request.getSession().getAttribute("booking");
		BookingDTO newBooking = bb.getBooking();
		
		int hotelId = newBooking.getHotel().getId();
		Calendar newCheckIn = newBooking.getCheckIn();
		Calendar newCheckOut = newBooking.getCheckOut();
		
		Map<RoomTypeDTO,Long> roomCapacities = roomDao.findRoomCapcaities(hotelId, maxPrice,newCheckIn,newCheckOut);
		Map<RoomTypeDTO,List<BookingDTO>> bookingsOfRoom = bookingDao.findBookingsOfRoom(hotelId, roomCapacities.keySet());
		
		List<BookingDTO> bookings;
		List<RoomTypeDTO> exhausted = new ArrayList<RoomTypeDTO>();
		for(RoomTypeDTO rt : roomCapacities.keySet()){
			bookings = bookingsOfRoom.get(rt);
			long capacity = roomCapacities.get(rt);
			for(BookingDTO exBooking : bookings){
				Calendar exCheckIn = exBooking.getCheckIn();
				Calendar exCheckOut = exBooking.getCheckOut();
				if(newCheckIn.equals(exCheckIn) || (newCheckIn.before(exCheckOut) && newCheckOut.after(exCheckIn))){
					capacity = capacity - 1;
					if(capacity==0){
						exhausted.add(rt);
						break;
					}
				}
			}
			roomCapacities.put(rt, capacity);
		}
		for(RoomTypeDTO rt : exhausted){
		    roomCapacities.remove(rt);
		}
		return roomCapacities;
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
	    
	    	BookingBean bb = (BookingBean) request.getSession().getAttribute("booking");
		SearchResultBean srb = (SearchResultBean) request.getSession().getAttribute("searchResult");
		
		if(bb.getBooking()==null) 
		    prepareBookingBean(request,bb);	    
		prepareSearchResultBean(request,srb);
		
		return "/searchResults.jsp";
	}
	
	private int genereatePIN(){
		int start = 1000;
		int end = 9999;
		Random random = new Random();
		long range = (long)end - (long)start + 1;
		long fraction = (long)(range * random.nextDouble());
		return (int)(fraction + start);
	}
	
	private BookingDTO createBooking(HttpServletRequest request) throws ParseException{
		int pin = genereatePIN();
		
		String checkInString = request.getParameter("checkInDate");
		String checkOutString = request.getParameter("checkOutDate");
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar checkInDate = Calendar.getInstance();
		checkInDate.setTime(df.parse(checkInString));
		
		Calendar checkOutDate = Calendar.getInstance();
		checkOutDate.setTime(df.parse(checkOutString));
		
		BookingDTO booking = new BookingDTO(pin,checkInDate,checkOutDate);
		
		int hotelId = Integer.parseInt(request.getParameter("city"));
		bookingDao.loadHotelToBooking(booking, hotelId);
		
		return booking;
	}
	
	private void prepareBookingBean(HttpServletRequest request, BookingBean bb){
		try {
			BookingDTO booking;
			booking = createBooking(request);
			bb.setBooking(booking);
			bb.setNumRooms(Integer.parseInt(request.getParameter("numRooms")));
			bb.setCurrentRoomNumber(1);
			bb.setPercentage(bb.getCurrentRoomNumber());
			bb.setMinRoom(1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}

	private void prepareSearchResultBean(HttpServletRequest request, SearchResultBean srb){
		Map<RoomTypeDTO,Long> searchResults = search(request);
//		TODO if(searchResults.keySet().size()==0) throw new NoSearchResultsFoundException();
		srb.setSearchResults(searchResults);
	}
}

