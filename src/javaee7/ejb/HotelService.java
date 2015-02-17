package javaee7.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javaee7.model.Hotel;

/**
 * Session Bean implementation class HotelService
 */
@Stateless
@LocalBean
public class HotelService {
	private static final String API_ADDRESS = "";
	
    /**
     * Default constructor. 
     */
    public HotelService() {
    }
    
    public static ArrayList<Hotel> find(double lat, double lng){
    	ResourceBundle resouce = ResourceBundle.getBundle("resources.secrets");
        System.out.println(resouce.getString("hello"));
    	return null;   	
    }

}
