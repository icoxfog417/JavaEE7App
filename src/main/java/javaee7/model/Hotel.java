package javaee7.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * Hotel Model
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Hotel {
    
    @Getter @Setter private String hotelNo = "";
    @Getter @Setter private String hotelName = "";
    @Getter @Setter private String hotelImageUrl = "";
    @Getter @Setter private String hotelThumbnailUrl = "";
    @Getter @Setter private String hotelInformationUrl = "";
    @Getter @Setter private String telephoneNo = "";
    @Getter @Setter private String faxNo = "";
    @Getter @Setter private String access = "";
    @Getter @Setter private double latitude = 0;
    @Getter @Setter private double longitude = 0;
    
    public Hotel(){
    }
    
}
