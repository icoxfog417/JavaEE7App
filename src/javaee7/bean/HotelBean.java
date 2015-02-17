package javaee7.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javaee7.service.HotelService;
import javaee7.model.Hotel;


@Named("HotelBean")
@RequestScoped
public class HotelBean implements Serializable{

	private ArrayList<Hotel> hotels = new ArrayList<Hotel>();
	
	public HotelBean(){
	}
	
	@PostConstruct
	public void initialize() {
		HotelService service = new HotelService();
		Hotel[] hotels = service.find(0, 0);
		this.hotels.addAll(Arrays.asList(hotels));
	}

	public ArrayList<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(ArrayList<Hotel> hotels) {
		this.hotels = hotels;
	}

}
