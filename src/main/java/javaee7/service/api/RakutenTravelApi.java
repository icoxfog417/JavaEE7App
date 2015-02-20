package javaee7.service.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
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
    private final static String SEARCH_API_FORMAT = "https://app.rakuten.co.jp/services/api/Travel/SimpleHotelSearch/20131024?applicationId=%s&latitude=%s&longitude=%s&searchRadius=2&datumType=1";
    private final static String INFO_API_FORMAT = "https://app.rakuten.co.jp/services/api/Travel/HotelDetailSearch/20131024?applicationId=%s&hotelNo=%s&datumType=1";
    private final static String RESOURCE_PATH = "resources.secrets";
    private final static double defaultLat = 35.694441;
    private final static double defaultLng = 139.692537;
    
    /**
     * Find the hotel around directed location.
     * @param lat: latitude
     * @param lng: longitude
     * @return
     */
    public static ArrayList<Hotel> findHotels(double lat, double lng){
        // create url
        String apiKey = getApiKey();
        String url = "";
        if(lat > 0 && lng > 0){
            url = String.format(SEARCH_API_FORMAT, apiKey, lat, lng);
        }else{
            url = String.format(SEARCH_API_FORMAT, apiKey, defaultLat, defaultLng);
        }
        
        // access api
        Client client = createClient();
        WebTarget target = client.target(URI.create(url));
        String response = target.request().get(String.class);
        
        // parse response (json format)
        ArrayList<Hotel> hotels = parseHotels(response);
        
        return hotels;
    }
    
    /**
     * Get hotel information from hotelNo
     * @param hotelNo
     * @return
     */
    public static Optional<Hotel> getHotelInfo(String hotelNo){
        // create url
        String apiKey = getApiKey();
        String url = String.format(INFO_API_FORMAT, apiKey, hotelNo);
        
        Client client = createClient();
        WebTarget target = client.target(URI.create(url));
        String response = target.request().get(String.class);
        
        // parse response (json format)
        ArrayList<Hotel> hotels = parseHotels(response);
        
        Hotel hotel = null;
        if(hotels.size() > 0){
            hotel = hotels.get(0);
        }
        
        return Optional.ofNullable(hotel);
    }
    
    
    /**
     * Parse the hotels json
     * @param hotelsNode
     * @return
     */
    private static ArrayList<Hotel> parseHotels(String hotelsJson){
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        
        try {
            JsonNode rootNode = mapper.readTree(hotelsJson);
            JsonNode hotelsNode = rootNode.get("hotels");

            for (final JsonNode hotelItem : hotelsNode) {
                JsonNode hotel = hotelItem.get("hotel");
                for (final JsonNode info : hotel) {
                    if(info.get("hotelBasicInfo") != null){
                        JsonNode baseInfo = info.get("hotelBasicInfo");
                        Hotel h = mapper.readValue(baseInfo.toString(), Hotel.class);
                        hotels.add(h);
                    }
                }
            }
        }catch(IOException ex){
            System.out.println(ex);
        }
        return hotels;
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
    
    /**
     * Get Rakuten API Key
     * It reads api key from resource file (secrets.properties's "API_KEY").
     * @return
     */
    private static String getApiKey(){
        ResourceBundle resouce = ResourceBundle.getBundle(RESOURCE_PATH);
        String apiKey = resouce.getString("API_KEY");
        return apiKey;
    }
    
    
}
