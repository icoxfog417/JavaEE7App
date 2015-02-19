package javaee7.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javaee7.model.Hotel;
import javaee7.service.api.RakutenTravelApi;

/**
 * Backing Bean for index.xhtml
 *
 */
@Named("IndexBean")
@RequestScoped
public class IndexBean implements Serializable{

	private ArrayList<Hotel> hotels = new ArrayList<Hotel>();
	
	public IndexBean(){
	}
	
	/**
	 * fetch initial hotels data
	 */
	@PostConstruct
	public void initialize() {
		this.hotels = RakutenTravelApi.findHotels(0, 0);
	}

	public ArrayList<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(ArrayList<Hotel> hotels) {
		this.hotels = hotels;
	}

}
