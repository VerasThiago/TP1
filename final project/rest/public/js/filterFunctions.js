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