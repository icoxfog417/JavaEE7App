package javaee7.service.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import javaee7.model.Hotel;


/**
 * Access Rakuten Hotel API
 * Hotel Search API
 * https://webservice.rakuten.co.jp/api/simplehotelsearch/
 * 
 * Hotel Information API
 * https://webservice.rakuten.co.jp/api/hoteldetailsearch/
 *
 */
public class RakutenTravelApi {
	private final static String TRAVEL_API_URL_FORMAT = "https://app.rakuten.co.jp/services/api/Travel/SimpleHotelSearch/20131024?applicationId=%s&latitude=%s&longitude=%s&searchRadius=2&datumType=1";
	private final static String RESOURCE_PATH = "resources.secrets";
	private final static double defaultLat = 35.694441;
	private final static double defaultLng = 139.692537;
	
	/**
	 * Find the hotel around directed location.
	 * It reads apikey information from resource file (secrets.properties's "API_KEY").
	 * @param lat: latitude
	 * @param lng: longitude
	 * @return
	 */
	public static ArrayList<Hotel> findHotels(double lat, double lng){
		
		// create url
    	ResourceBundle resouce = ResourceBundle.getBundle(RESOURCE_PATH);
        String apiKey = resouce.getString("API_KEY");
        String url = "";
        if(lat > 0 && lng > 0){
            url = String.format(TRAVEL_API_URL_FORMAT, apiKey, lat, lng);
        }else{
        	url = String.format(TRAVEL_API_URL_FORMAT, apiKey, defaultLat, defaultLng);
        }
        
        // access api
        Client client = createClient();
    	WebTarget target = client.target(URI.create(url));
        String response = target.request().get(String.class);
        
        // parse response (json format)
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Hotel> results = new ArrayList<Hotel>();
        
        try {
            JsonNode rootNode = mapper.readTree(response);
            JsonNode hotels = rootNode.get("hotels");
            
            if(hotels != null && hotels.isArray()){
        	    for (final JsonNode hotelItem : hotels) {
        	    	JsonNode hotel = hotelItem.get("hotel");
        	    	for (final JsonNode info : hotel) {
        	    		if(info.get("hotelBasicInfo") != null){
        	    			JsonNode baseInfo = info.get("hotelBasicInfo");
        	    			Hotel h = mapper.readValue(baseInfo.toString(), Hotel.class);
        	    			results.add(h);
        	    		}
        	    	}
        	    }
            }        	
        	
        }catch(IOException ex){
        	System.out.println(ex);
        }
    	
    	return results;
	}
	
	
	/**
	 * Create http access client.
	 * It reads proxy information from resource file(secrets.properties's "HTTP_PROXY").
	 * @return
	 */
	private static Client createClient(){
    	ResourceBundle resouce = ResourceBundle.getBundle(RESOURCE_PATH);
        String httpProxy = resouce.getString("HTTP_PROXY");
		
        Client client = null;
        
        if(httpProxy.isEmpty()){
        	client = ClientBuilder.newClient();        	
        }else{
            ClientConfig config = new ClientConfig();
            config.connectorProvider(new ApacheConnectorProvider());
            config.property(ClientProperties.PROXY_URI, httpProxy);
            client = ClientBuilder.newClient(config);
        }
        
        return client;
	}
	
}
