function startXhr() {
    xhr = new XMLHttpRequest();

    xhr.open("get", "js/jik_xml.jsp", true);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                prcFunc();
            }else{
                alert("요청 실패:" + xhr.status);
            }
        }
    }
    xhr.send();
}   // startXhr();

function prcFunc() {
    var data = xhr.responseXML;
    // alert(data);     // 확인용

    // SQL parameter 값 가져오기.
    var jikwonNode = data.getElementsByTagName("Jikwon");
    var noNode = data.getElementsByTagName("no");
    var nameNode = data.getElementsByTagName("name");
    var jikNode = data.getElementsByTagName("jik");
    var payNode = data.getElementsByTagName("pay");
    var ibsaNode = data.getElementsByTagName("ibsail");
    var genNode = data.getElementsByTagName("gen");

    var str = "<table border='1'>";
    str += "<tr><th>직원번호</th><th>직원이름</th>" +
        "<th>직급</th><th>연봉</th><th>입사일</th><th>성별</th></tr>";

    // alert(jikwonNode.length);   // 확인용

    for (var i = 0; i < jikwonNode.length; i++) {
        str += "<tr>";
        str += "<td>" + noNode[i].childNodes[0].nodeValue + "</td>";
        str += "<td>" + nameNode[i].childNodes[0].nodeValue + "</td>";
        str += "<td>" + jikNode[i].childNodes[0].nodeValue + "</td>";
        str += "<td>" + payNode[i].childNodes[0].nodeValue + "</td>";
        str += "<td>" + ibsaNode[i].childNodes[0].nodeValue + "</td>";
        str += "<td>" + genNode[i].childNodes[0].nodeValue + "</td>";
        str += "</tr>";
    }
    str += "</table>";
    document.getElementById("disp").innerHTML = str;
}