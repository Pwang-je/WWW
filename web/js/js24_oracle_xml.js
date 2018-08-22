function startXhr() {
    xhr = new XMLHttpRequest();

    xhr.open("get", "js/sang_xml.jsp", true);

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
    var data = xhr.responseXML;
    // alert(data);
    var sangNode = data.getElementsByTagName("Product");
    var coNode = data.getElementsByTagName("code");
    var saNode = data.getElementsByTagName("name");
    var suNode = data.getElementsByTagName("qty");
    var daNode = data.getElementsByTagName("unitp");

    var str = "<table border='1'>";
    str += "<tr><th>코드</th><th>상품명</th>" +
                "<th>수량</th><th>단가</th></tr>";

    // alert(sangNode.length);

    for (var i = 0; i < sangNode.length; i++) {
        str += "<tr>";
        str += "<td>" + coNode[i].childNodes[0].nodeValue + "</td>";
        str += "<td>" + saNode[i].childNodes[0].nodeValue + "</td>";
        str += "<td>" + suNode[i].childNodes[0].nodeValue + "</td>";
        str += "<td>" + daNode[i].childNodes[0].nodeValue + "</td>";
        str += "</tr>";
    }
    str += "</table>";
    document.getElementById("disp").innerHTML = str;

}   // processFunc();