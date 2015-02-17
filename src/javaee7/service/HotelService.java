package javaee7.service;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import javaee7.model.Hotel;

/**
 * Session Bean implementation class HotelService
 */
@Path("/hotels")
public class HotelService {
	private static final String API_ADDRESS = "";
	
    /**
     * Default constructor. 
     */
    public HotelService() {
    }
    
    @GET
    @Path("/find")
    @Produces({ "application/json" })
    public Hotel[] find(
    		@DefaultValue("0.0") @QueryParam("lat") double lat, 
    		@DefaultValue("0.0") @QueryParam("lng") double lng){
    	
    	return RakutenTravelResource.findHotels(lat, lng);
    }

}
