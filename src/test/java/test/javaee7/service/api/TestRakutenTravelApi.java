package test.javaee7.service.api;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import javaee7.model.Hotel;
import javaee7.service.api.RakutenTravelApi;


public class TestRakutenTravelApi {

	@Test
	public void test() {
		ArrayList<Hotel> hotels = RakutenTravelApi.findHotels(0, 0);
		for(Hotel h : hotels){
			System.out.println(h.getHotelName());			
		}
	}

}
