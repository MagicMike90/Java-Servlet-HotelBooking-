package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.dto.RoomTypeDTO;

public class SearchResultBean implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<RoomTypeDTO,Long> searchResults;
	private Map<Integer,RoomTypeDTO> roomTypes;
	
    public SearchResultBean() {}
    
    public void addedToBooking(RoomTypeDTO rt){
    	RoomTypeDTO roomTypeKey = roomTypes.get(rt.getId());
    	if(searchResults.containsKey(roomTypeKey)){
    		long capacity = searchResults.get(roomTypeKey);
    		capacity = capacity - 1;
    		if(capacity==0) searchResults.remove(roomTypeKey);
    		else searchResults.put(roomTypeKey, capacity);
    	}
    }
    
    public void removedFromBooking(RoomTypeDTO rt){
    	RoomTypeDTO roomTypeKey = roomTypes.get(rt.getId());
    	if(searchResults.containsKey(roomTypeKey)){
    		long capacity = searchResults.get(roomTypeKey);
    		capacity = capacity + 1;
    		searchResults.put(roomTypeKey, capacity);
    	}
    	else{
    		searchResults.put(roomTypeKey, new Long(1));
    	}
    		
    }
	public Map<RoomTypeDTO, Long> getSearchResults() {
		return searchResults;
	}
	public void setSearchResults(Map<RoomTypeDTO, Long> searchResults) {
		this.searchResults = searchResults;
		roomTypes = new HashMap<Integer,RoomTypeDTO>();
    	for(RoomTypeDTO rt : searchResults.keySet()){
    		roomTypes.put(rt.getId(), rt);
    	}
	}
	
	public Set<RoomTypeDTO> getRoomTypes(){
		return searchResults.keySet();
	}
}
