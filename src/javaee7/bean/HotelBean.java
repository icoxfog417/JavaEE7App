package javaee7.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javaee7.ejb.HotelService;
import javaee7.model.Hotel;


@Named("HotelBean")
@RequestScoped
public class HotelBean implements Serializable{

	private ArrayList<Hotel> hotels = new ArrayList<Hotel>();
	
	public HotelBean(){
	}
	
	@PostConstruct
	public void initialize() {
		Hotel h1 = new Hotel("1", "Hotel Smansa", "http://samansa.com", 35.6761518,139.7768192);
		Hotel h2 = new Hotel("2", "Hotel Ruwanda", "http://ruwanda.com", 35.617017,139.661981);
		Hotel h3 = new Hotel("3", "Hotel Pasific", "http://pasific.com", 35.629399,139.73689);
		this.hotels.addAll(Arrays.asList(h1, h2, h3));
	}

	public ArrayList<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(ArrayList<Hotel> hotels) {
		this.hotels = hotels;
	}

}
