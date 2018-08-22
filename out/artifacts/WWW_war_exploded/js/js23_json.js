var xhr;

function startXhr() {
    xhr = new XMLHttpRequest();

    xhr.open("get", "js/bb.json", true);

    xhr.onreadystatechange = function(){
        //alert(xhr.readyState);
        if(xhr.readyState === 4){
            //alert("통신상태 양호");
            //alert(xhr.status);
            if(xhr.status === 200){
                //alert("요청 성공");
                processFunc();
            }else{
                alert("요청 실패:" + xhr.status);
            }
        }
    }
    xhr.send(null);
}

function processFunc() {
    const data = xhr.responseText;
    const parseData = JSON.parse(data); // 객체화(배열)
    alert(parseData.sangpum.length);

    var ss = "";
    for (var i = 0; i < parseData.sangpum.length; i++) {
        ss += parseData.sangpum[i].code + " " +
            parseData.sangpum[i].sang + "<br>";
    }
    document.getElementById("disp").innerHTML = ss;
}