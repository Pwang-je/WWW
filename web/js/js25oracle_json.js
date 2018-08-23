function startXhr() {
    xhr = new XMLHttpRequest();

    xhr.open("get", "js/sang_json.jsp", true);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                processFunc();
            }else{
                alert("요청 실패:" + xhr.status);
            }
        }
    }
    xhr.send();
}   // startXhr();

function processFunc() {
    var data = xhr.responseText;    // Json
    var parseData = JSON.parse(data);

    var ss = "";
    for (var i = 0; i < parseData.sang.length; i++) {
        ss += parseData.sang[i].code + " " +
            parseData.sang[i].sang + " " +
            parseData.sang[i].su + " " +
            parseData.sang[i].dan + "<br>";
    }
    document.getElementById("disp").innerHTML = data;
}