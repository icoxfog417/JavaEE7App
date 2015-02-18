package test.javaee7.service.api;

import static org.junit.Assert.*;
import org.junit.Test;
import javaee7.service.api.RakutenTravelApi;


public class TestRakutenTravelApi {

	@Test
	public void test() {
		RakutenTravelApi.findHotels(0, 0);
	}

}
