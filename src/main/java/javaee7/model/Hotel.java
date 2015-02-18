package javaee7.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Hotel {
	
	@Getter @Setter private String hotelNo;
	@Getter @Setter private String hotelName;
	@Getter @Setter private String hotelInformationUrl;
	@Getter @Setter private double latitude;
	@Getter @Setter private double longitude;
	
	public Hotel(){		
	}

	public Hotel(String hotelNo, String hotelName, String hotelInformationUrl, Double latitude, Double longitude){
		this.hotelNo = hotelNo;
		this.hotelName = hotelName;
		this.hotelInformationUrl = hotelInformationUrl;
		this.latitude = latitude;
		this.longitude = longitude;
	}	
	
}
