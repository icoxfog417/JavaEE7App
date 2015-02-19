
/**
 * Initialize the Google Map.
 * (it'll be good to use Geolocation API to set center, but this API is not work. so set default location same as server's default.
 */
function initialize(latitude, longitude) {
	var lat = latitude;
	var lng = longitude;
	if(arguments.length < 2){
		lat = 35.694441;
		lng = 139.692537;
	}
	
	var mapOptions = {
			center: new google.maps.LatLng(lat, lng),
			zoom: 15,
			mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	var map = new google.maps.Map(document.getElementById("map"), mapOptions);
	setMarkers(map);	
}

/**
 * Set markers on the map. Markers information is read from table(#tabHotels).
 * @param map: Google Map object
 */
function setMarkers(map){
	//TODO: clear the markers
	
	//create marker on the map
	var markers = [];
	var hotels = $("#tabHotels tr");
	for(var i = 0; i < hotels.length; i++){
		var h = $(hotels[i]).find("td");
		
		var title = h[0].innerText;
		var lat = parseFloat(h[1].innerText);
		var lng = parseFloat(h[2].innerText);
		
		var m = new google.maps.Marker({
			position: new google.maps.LatLng(lat, lng),
			title: title
		});

		var contentString="<div>" + title + "</div>";
        var pop = new google.maps.InfoWindow({
            content: contentString
        });
		markers.push([m, pop]);
	}
	
	markers.forEach(function(mp){
		var m = mp[0];
		var p = mp[1];
		google.maps.event.addListener(m, "click", function() {
			p.open(map, m);
        });
		m.setMap(map);
	})
	
}

$(function(){
	//first set position a little out of alignment, so wait a bit.
	setTimeout(function(){
		initialize();		
	}, 500);
})
