@(list:Seq[MapsData])


function googleMaps(lat,lng) {
    var marker, i;
    var crd;
    var map;

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

            i = 0;
            @for(t <- list) {

                marker = new google.maps.Marker({
                    position: new google.maps.LatLng(@t.lat, @t.lng),
                    map: map,
                    icon: "@t.icon"
                });

                google.maps.event.addListener(marker, 'click', (function(marker, i) {
                    return function() {
                        infowindow.setContent('<div style="color: red;">'+'<p>Essa é o @t.name</p>'+'</div>');
                        infowindow.open(map, marker);
                    }
                })(marker, i));
                /* Remover borda fixa dos marcadores quando clicados*/
                i = i+1;
            }




        });
    }
    else{ // Same Here
        alert("I'm sorry, but geolocation services are not supported by your browser.");
        map = new google.maps.Map(document.getElementById('map'), {
            zoom: 18,
            center: new google.maps.LatLng(locations[0][1], locations[0][2]),
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });



        for (i = 0; i < locations.length; i++) { // Resto dos marcadores
            marker = new google.maps.Marker({
                position: new google.maps.LatLng(locations[i][1], locations[i][2]),
                map: map,
                icon: icons[locations[i][3]][0]
            });

            google.maps.event.addListener(marker, 'click', (function(marker, i) {
                return function() {
                    infowindow.setContent(informations[i][0]);
                    infowindow.open(map, marker);
                }
            })(marker, i));
            /* Remover borda fixa dos marcadores quando clicados*/
        }
    }
    var infowindow = new google.maps.InfoWindow();
}