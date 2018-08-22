var xhr;

function startJhr() {
    xhr = new XMLHttpRequest();
    xhr.open("get", "http://openapi.seoul.go.kr:8088/sample/xml/SeoulLibraryTime/1/5/", true);
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                processFunc();
            }else{
                alert("요청 실패:" + xhr.status);
            }
        }
    }
    xhr.send(null);
}

// XML
function processFunc() {
    var xmlData = xhr.responseXML;
    // var parseData = JSON.parse(xmlData); // 객체화(배열)
    // alert(parseData.code);   // 확인용.

    var LibraryName  = "";  // 도서관명
    var lName = xmlData.getElementsByTagName("LBRRY_NAME");
    for (var i = 0; i < lName.length; i++ ) {
        LibraryName += + lName[i].getAttribute("LBRRY_NAME") +
        " : " + lName[i].firstChild.data + "\n";
    }

    var LibraryAddr = "";    // 주소
    var lAddr = xmlData.getElementsByTagName("ADRES");
    for (var i = 0; i < lAddr.length; i++) {
        LibraryAddr += + lAddr[i].getAttribute("ADRES") +
            " : " + lAddr[i].firstChild.data + "\n";
    }

    var LibraryTel = "";    // 전화
    var lTel = xmlData.getElementsByTagName("TEL_NO");
    for (var i = 0; i < lTel.length; i++) {
        LibraryTel += lTel[i].getAttribute("TEL_NO") +
            " : " + lTel[i].firstChild + "\n";
    }

    document.getElementById("LibraryName").innerHTML = LibraryName;
    document.getElementById("LibraryAddr").innerHTML = LibraryAddr;
    document.getElementById("LibraryTel").innerHTML = LibraryTel;

}