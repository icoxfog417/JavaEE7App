package test.javaee7.ejb;

import static org.junit.Assert.*;
import org.junit.Test;
import javaee7.ejb.HotelService;


public class TestHotelService {

	@Test
	public void test() {
		HotelService.find(0, 0);
	}

}
