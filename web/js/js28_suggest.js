var xhr;
var checkFirst = loopSend = false;
var lastKeyword = "";

function start() {
    // alert("aa");
    if (checkFirst === false) {
        loopSend = true;
        setTimeout("sendKeyword()", 1000);   // 1초마다 sendKeyword 를 부름.
    }
}

function sendKeyword() {
    if (loopSend === false) {
        return;
    }

    var keyword = document.frm.keyword.value;
    // console.log(keyword);
    if (keyword === "") {
        lastKeyword = "";
        hide("suggest")
    } else if (keyword !== "lastKeyword") {
        lastKeyword = keyword;
        if (keyword !== "") {
            var params = "keyword=" + keyword;

            // xhr initialize
            xhr = new XMLHttpRequest();
            xhr.open("post", "js/suggest.jsp", true);
            xhr.onreadystatechange = function(){
                if(xhr.readyState === 4){
                    if(xhr.status === 200){
                        prcFunc();
                    }else{
                        alert("fail~fail~fail~~" + xhr.status);
                    }
                }
            }
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.send(params);
        }
    }
}

function hide(ele) {
    var e = document.getElementById(ele);
    if (e.style.display = "none") {
    }
}
function show(ele) {
    var e = document.getElementById(ele);
    if (e.style.display = "block") {
    }
}