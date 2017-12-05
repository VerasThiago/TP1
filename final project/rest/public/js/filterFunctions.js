function myFunction() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    ul = document.getElementById("myUL");
    li = ul.getElementsByTagName("li");
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("a")[0];
        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";

        }
    }
}

function verasFunction(type){
    var input, filter, ul, li, a, i;
    ul = document.getElementById("myUL");
    li = ul.getElementsByTagName("li");
    if(type == 'todos'){
        for(let i = 0; i < li.length; i++){
            li[i].style.display = '';
        }
    }
    else{
        for(let i = 0; i < li.length; i++){
            a = li[i].getElementsByTagName("div")[0].className
            if(a == type) li[i].style.display = '';

            else  li[i].style.display = 'none';
        }
    }
}

function dist(latA, lngA, latB, lngB, i, base){
    var d2r = 0.017453292519943295769236;
    var long = (lngB - lngA) * d2r;
    var lat = (latB - latA) * d2r;
    var temp_sin = Math.sin(lat/2.0);
    var temp_cos = Math.cos(latA * d2r);
    var temp_sin2 = Math.sin(long/2.0);
    var a = (temp_sin * temp_sin) + (temp_cos * temp_cos) * (temp_sin2 * temp_sin2);
    var c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));
    var dir = 6368.1 * c; 

    if(dir <= base) return true;
    return false;
}

function nearby (){
    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position){

            var a = position.coords.latitude;
            var b = position.coords.longitude;

            var base = prompt("Digite a distância em Km: ", "4.5");
            while(isNaN(base)) base = prompt("Numero inválido, digite a distância em Km: ", "4.5");
        
            var ul, li, i;
            ul = document.getElementById("myUL");
            li = ul.getElementsByTagName("li");

            for (i = 0; i < locations.length; i++) {
                if(dist(a,b,locations[i][1],locations[i][2],i, base)) li[i].style.display = '';
                else li[i].style.display = 'none';
            }

        });
    }

}
