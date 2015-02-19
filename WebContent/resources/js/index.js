var G_MAP = null;
var G_MARKERS = [];
var G_ACTIVE_WINDOW = null;

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
	G_MAP = new google.maps.Map(document.getElementById("map"), mapOptions);
	setMarkers(G_MAP);	
}

/**
 * Set markers on the map. Markers information is read from table(#tabHotels).
 * @param map: Google Map object
 */
function setMarkers(map){
	//TODO: clear the markers
	
	//create marker on the map
	var hotels = $("#tabHotels tr");
	for(var i = 0; i < hotels.length; i++){
		var h = $(hotels[i]).find("td");
		
		var no = $(h[0]).find("span").get(0).id;
		var title = h[0].innerText;
		var lat = parseFloat(h[1].innerText);
		var lng = parseFloat(h[2].innerText);
		
		//create marker
		var m = new google.maps.Marker({
			position: new google.maps.LatLng(lat, lng),
			title: title,
			no: no
		});
		
		google.maps.event.addListener(m, "click", function(){
			openInfoWindow(map, this);
		});

		G_MARKERS.push(m);
	}
	
	G_MARKERS.forEach(function(m){
		m.setMap(map);		
	})

}


function openInfoWindow(map, marker){
	var contentFormat = "<div class='infoView'><TITLE><ACCESS><TEL><IMG></div>";
	contentFormat = contentFormat.replace("<TITLE>", "<a href='{link}' target='_blank'>{name}</a><br/>");
	contentFormat = contentFormat.replace("<ACCESS>", "<div>{access}</div>");
	contentFormat = contentFormat.replace("<TEL>", "<div>{tel}</div>");
	contentFormat = contentFormat.replace("<IMG>", "<img src='{src}' />");

	$.getJSON("rest/hotels/{no}".replace("{no}", marker.no), function(h){
		var content = contentFormat.replace("{link}", h.hotelInformationUrl).replace("{name}", h.hotelName);
		content = content.replace("{access}", h.access);
		content = content.replace("{tel}", h.telephoneNo);
		content = content.replace("{src}", h.hotelThumbnailUrl);
        var pop = new google.maps.InfoWindow({
            content: content
        });
        if(G_ACTIVE_WINDOW != null){
        	G_ACTIVE_WINDOW.close();
        }
        G_ACTIVE_WINDOW = pop;
        pop.open(map, marker);
	})
}

$(function(){
	//first set position a little out of alignment, so wait a bit.
	setTimeout(function(){
		initialize();		
	}, 500);
	
	//by css is not work...
	$("tr.hotelinfo").hover(
		function(){ $(this).css("background-color", "gainsboro") },
		function(){ $(this).css("background-color", "white") }
	)
	
	$(".hotelinfo").click(function(){
		var no = $(this).closest("tr").find(".caption").find("span").get(0).id;
		var target = null;
		G_MARKERS.forEach(function(m){
			if(m.no == no){
				target = m;
			}
		});
		
		if(target != null){
			openInfoWindow(G_MAP, target);
		}
	})
	
})
