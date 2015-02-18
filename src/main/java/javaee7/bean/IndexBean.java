package javaee7.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javaee7.model.Hotel;
import javaee7.service.api.RakutenTravelApi;

@Named("IndexBean")
@RequestScoped
public class IndexBean implements Serializable{

	private ArrayList<Hotel> hotels = new ArrayList<Hotel>();
	
	public IndexBean(){
	}
	
	@PostConstruct
	public void initialize() {
		Hotel[] hotels = RakutenTravelApi.findHotels(0, 0);
		this.hotels.addAll(Arrays.asList(hotels));
	}

	public ArrayList<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(ArrayList<Hotel> hotels) {
		this.hotels = hotels;
	}

}
