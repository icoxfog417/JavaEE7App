package javaee7.service.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javaee7.model.Hotel;

public class RakutenTravelApi {
	
	public static Hotel[] findHotels(double lat, double lng){
    	ResourceBundle resouce = ResourceBundle.getBundle("resources.secrets");
        System.out.println(resouce.getString("API_KEY"));
        
        Hotel h1 = new Hotel("1", "Hotel Smansa", "http://samansa.com", 35.6761518,139.7768192);
		Hotel h2 = new Hotel("2", "Hotel Ruwanda", "http://ruwanda.com", 35.617017,139.661981);
		Hotel h3 = new Hotel("3", "Hotel Pasific", "http://pasific.com", 35.629399,139.73689);
		
		Hotel[] hotels =  new Hotel[]{h1, h2, h3};
    	return hotels;
	}
	
}
