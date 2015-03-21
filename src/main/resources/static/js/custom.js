   var geocoder;
    var map;
    var infowindow ;
    var userLocation;
    function codeAddress() {
      var address = document.getElementById('address').value;
  geocoder.geocode( { 'address': address+ ', Belgium'}, function(results, status) {//belgium staat zodat er enkel in belgie wordt gezocht
    if (status == google.maps.GeocoderStatus.OK) {
      map.setCenter(results[0].geometry.location);
      /*var marker = new google.maps.Marker({
          position: results[0].geometry.location,
          map: map,          
          title: 'Hello World!'
      });
      marker.setIcon('http://maps.google.com/mapfiles/ms/icons/blue-dot.png')*///Wordt gebruikt om marker op gezochte plaatsnaam te zetten
    } else {
      alert('Geocode was not successful for the following reason: ' + status);
    }
  });
  map.setZoom(14); 
}
 function showAutomaten(){
      	$.get("/api/broodautomaat", function(data) {
      		var mydata = JSON.stringify(data);
      		var json = JSON.parse(mydata);
      		
      		for(var i=0;i<json.length;i++){
      			// KAN GEBRUIKT WORDEN VOOR MET LONG/LAT te zoeken ipv adres - Klein foutje momenteel maar zo goed als bruikbaar
      			/*if(json[i].geolocatie){

      			  var myLatLng = new google.maps.LatLng(json[i].geolocatie.x, json[i].geolocatie.y);
      			
      			  var marker = new google.maps.Marker({
      		          position: myLatLng,
      		          map: map
      		      });
      			marker.setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png')
      			}else{*/
      				
      				var url = "https://maps.googleapis.com/maps/api/geocode/json?address="+json[i].adres.straat+"+"+json[i].adres.nummer+"+"+json[i].adres.postcode+"+"+json[i].adres.gemeente;
      			//alert(json[i].adres.straat+json[i].adres.nummer+json[i].adres.postcode+json[i].adres.gemeente);
      				addMarkers(url, json[i].broodBeschikbaar, json[i]);
      			/*}*/
      		}
      	});	
}
 function addMarkers(url, beschikbaar, json){
		$.get(url, function (data2) {
			var mydata2 = JSON.stringify(data2);
	  		var json2 = JSON.parse(mydata2);
	  		var myLatLng = new google.maps.LatLng(json2.results[0].geometry.location.lat,json2.results[0].geometry.location.lng);
	  		var adres =  json2.results[0].formatted_address;
	  		
	  	var marker = new google.maps.Marker({
       position: myLatLng,
       map: map,
       json: json,
       adres: adres,
	  	}); 
	  	
	  	if(beschikbaar == true){
	  		marker.setIcon('bakeryok.png');
		  	createModal(marker.json, marker.adres);
	  	}else{
	  		marker.setIcon('bakerynok.png');
		  	createModal(marker.json,marker.adres);
	  	}

			google.maps.event.addListener(marker, 'click', function() {
			    $('#'+marker.json._id).modal('show');
			  });
			
		});
}

function fillAddress(){
	var address = getUserLocation();
	if(address!="Leuven"){
		

		var res=address.split(",");
		var straatSplit = res[0].split(" ");
		var nr = straatSplit[straatSplit.length-1];
		var gemeenteSplit = res[1].split(" ");
		var straat="";
		for(var i =0; i<straatSplit.length-1;i++){
			straat += straatSplit[i];
			straat +=" ";
		}
		
		
		document.getElementById('nummer').value = nr;
		document.getElementById('postcode').value= gemeenteSplit[1];
		document.getElementById('gemeente').value= gemeenteSplit[2];
		document.getElementById('straat').value=straat;
		alert("We hebben je locatie gevonden en ingevuld! Kijk dit even na en verbeter indien nodig.")
		
	}else{
		alert("Helaas vinden we je locatie niet. Gelieve deze zelf in te geven");
	}
	
}


 
function createModal(json, adres){
	var modal = "<div id='"+json._id+"' class='ui basic modal'>"+
	  "<i class='close icon'></i>"+
	  "<div class='header'>"+
	  "Is er nog brood?"+
	  "</div>"+
	  "<div class='content'>"+
	 	
	   "</div>"+
	    "<div class='description'>"+
	    "<form  class='ui inverted form'>"+

        "<div class='field'>"+
         " <div class='ui toggle checkbox'>";
         if(json.witBroodBeschikbaar){
          modal+="  <input type='checkbox' name='wit' checked ='checked'>";
         }else{
        	 modal+="  <input type='checkbox' name='wit'>";
         }
         modal+=  " <label>Wit Brood</label>"+
          "</div>"+
        "</div>"+
        
        

        "<div class='field'>"+
         " <div class='ui toggle checkbox'>";
         if(json.bruinBroodBeschikbaar){
             modal+="  <input type='checkbox' name='bruin' checked ='checked'>";
            }else{
           	 modal+="  <input type='checkbox' name='bruin'>";
            }
          modal+=" <label>Grijs Brood</label>"+
          "</div>"+
       " </div>"+

        "<div class='field'>"+
         " <div class='ui toggle checkbox'>";
         if(json.speciaalBroodBeschikbaar){
             modal+="  <input type='checkbox' name='speciaal' checked ='checked'>";
            }else{
           	 modal+="  <input type='checkbox' name='speciaal'>";
            }
           modal+=" <label>Speciaal Brood</label>"+
          "</div>"+

        "</div>"+

        "<div class='ui error message'>"+
         " <div class='header'>Er is iets misgelopen, probeer later opnieuw.</div>"+
        "</div>"+

        "<div class='ui submit orange button'>Update</div>"+
        '<a href="http://maps.google.com/maps?saddr='+getUserLocation()+'&daddr='+adres+'" target="_blank" > <div  class="ui orange button">Get Directions</div>'+

      "</form>"+
	    "</div>"+
	  "</div>"+
	"</div>"+
	"<script>"+
	"$('.ui.checkbox').checkbox();"+
	"</"+"script>";
	
	$( "#modals" ).append(modal);
}


//Note: This example requires that you consent to location sharing when
//prompted by your browser. If you see a blank space instead of the map, this
//is probably because you have denied permission for location sharing.
var map;

function initialize() {
	setUserLocation('"Leuven"');
  geocoder = new google.maps.Geocoder();
  var mapOptions = {
   zoom: 14
 };
 map = new google.maps.Map(document.getElementById('map-canvas'),
   mapOptions);
 showAutomaten();


// Try HTML5 geolocation
if(navigator.geolocation) {
 navigator.geolocation.getCurrentPosition(function(position) {
   var pos = new google.maps.LatLng(position.coords.latitude,
    position.coords.longitude);
   var lat = pos.lat();
   var lng = pos.lng();

   /*var infowindow = new google.maps.InfoWindow({
     map: map,
     position: pos,
     content: 'We hebben je locatie gevonden!'
   });
   */	//ENABLE DIT VOOR EEN INFOWINDOW TE TONEN!!!
   var url="https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lng;
   $.getJSON(url, function (data) {
     var mydata = JSON.stringify(data);
     var json = JSON.parse(mydata);

     document.getElementById('address').value = json.results[0].formatted_address;
     setUserLocation(JSON.stringify(json.results[0].formatted_address));
   });
   map.setCenter(pos);
 }, function() {
   handleNoGeolocation(true);
 });
} else {
 // Browser doesn't support Geolocation
 handleNoGeolocation(false);
}
}
function setUserLocation(userLocation)
{
    this.userLocation = userLocation;
}

function getUserLocation()
{
    return userLocation.substring(1, userLocation.length-1);
}


function handleNoGeolocation(errorFlag) {
  if (errorFlag) {
   var content = 'Helaas konden we je beginpositie niet vinden. We hebben als basis Leuven gekozen! Als je een andere stad/gemeente wil, vul dan de naam of postcode in in het zoekveld';
 } else {
   var content = 'Error: Your browser doesn\'t support geolocation.';
 }

 var options = {
   map: map,
   position: new google.maps.LatLng(50.883333, 4.7),
   content: content
 };

//var infowindow = new google.maps.InfoWindow(options);  //Dit kan een boodscap geven aan de user
map.setCenter(options.position);

}
google.maps.event.addDomListener(window, 'load', initialize);