function startXhr(params) {
    xhr = new XMLHttpRequest();

    xhr.open("get", "js/js25_ex.jsp" + params, true);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                prcFunc();
            }else{
                alert("fail~fail~fail~~" + xhr.status);
            }
        }
    }
    xhr.send();
}   // startXhr();

function prcFunc() {

    var jdata = xhr.responseText;
    var parseJdata = JSON.parse(jdata);
    // alert(parseJdata.jikwon.length);

    var count = 0;
    var str = "";
    for (var i = 0; i < parseJdata.jikwon.length; i++) {
        str += parseJdata.jikwon[i].no + " " +
            parseJdata.jikwon[i].name + " " +
            parseJdata.jikwon[i].jik + " " +
            parseJdata.jikwon[i].gen + "<br>";
        count++;
    }
    // str += "인원: " + count;

    document.getElementById("disp").innerHTML = str;
}