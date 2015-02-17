package javaee7.model;

public class Hotel {
	
	private String hotelNo;
	private String hotelName;
	private String hotelInformationUrl;
	private double latitude;
	private double longitude;	
	
	public Hotel(){		
	}

	public Hotel(String hotelNo, String hotelName, String hotelInformationUrl, Double latitude, Double longitude){
		this.hotelNo = hotelNo;
		this.hotelName = hotelName;
		this.hotelInformationUrl = hotelInformationUrl;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String getHotelNo() {
		return hotelNo;
	}

	public void setHotelNo(String hotelNo) {
		this.hotelNo = hotelNo;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelInformationUrl() {
		return hotelInformationUrl;
	}

	public void setHotelInformationUrl(String hotelInformationUrl) {
		this.hotelInformationUrl = hotelInformationUrl;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}
