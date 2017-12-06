
lugar = ""
function googleMaps(lat,lng) {
   var marker, i;
   var crd;
   var map;
   //console.log('lugar = ' + lugar);
   if(navigator.geolocation) {
       navigator.geolocation.getCurrentPosition(function(position){

           var loc;
           // Problem: lat,lng can't get position.coords to pass to map out of natvigator function, so I needed to add map into and markers too.
           if(lat == null) loc = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
           else loc = new google.maps.LatLng(lat,lng);

           map = new google.maps.Map(document.getElementById('map'), {
               zoom: 18,
               center: loc,
               mapTypeId: google.maps.MapTypeId.ROADMAP
           });

           marker = new google.maps.Marker({ // Marcador do User
               position: new google.maps.LatLng(position.coords.latitude,position.coords.longitude),
               map: map,
               icon: 'http://maps.google.com/mapfiles/kml/pal3/icon32.png'
           });

           lugar = new google.maps.Marker({ // Marcador do Novo Local
               position: new google.maps.LatLng(position.coords.latitude + 0.00020,position.coords.longitude + 0.00020),
               map: map,
               draggable: true
           });
                             

       });
   }
   else{ // Same Here
       alert("I'm sorry, but geolocation services are not supported by your browser.");
       map = new google.maps.Map(document.getElementById('map'), {
           zoom: 18,
           center: new google.maps.LatLng(locations[0][1], locations[0][2]),
           mapTypeId: google.maps.MapTypeId.ROADMAP
       });

   }
   var infowindow = new google.maps.InfoWindow();
}
function testResults(){
   var x = document.getElementById("frm1");
       var text = [];
       var i;
       for (i = 0; i < x.length-1 ;i++) {
           //console.log(i + ' = ' + x.elements[i].value);
           if(i == 2 || i == 3){
               text[i+2] = x.elements[i].value;
               text[i] = i == 2 ? lugar.position.lat():lugar.position.lng()

           }
           else
               text[i] = x.elements[i].value;
       }
       for(i = 0; i < 6; i++){
           console.log('text[' + i + '] = ' + text[i]);
       }
       update(text);
       console.log(locations[i]);
}
function update(text){
    console.log('locations size = ' locations.length);
    console.lof(locations[locations.length]);
}

