package javaee7.service;

import java.util.ArrayList;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import javaee7.model.Hotel;
import javaee7.service.api.RakutenTravelApi;

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
    	
    	ArrayList<Hotel> hotels =  RakutenTravelApi.findHotels(lat, lng);
    	return hotels.toArray(new Hotel[hotels.size()]);
    }

    @GET
    @Path("/{hotelNo}")
    @Produces({ "application/json" })
    public Hotel get(@PathParam("hotelNo") String hotelNo){    	
    	return RakutenTravelApi.getHotelInfo(hotelNo).orElse(new Hotel());
    }
    
}

