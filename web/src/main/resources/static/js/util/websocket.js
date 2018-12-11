var websocket = null;

//判断当前浏览器是否支持WebSocket
if('WebSocket' in window){
    websocket = new WebSocket("ws://localhost:8080/websocket");
    //websocket = new WebSocket("ws://47.93.23.219:8333/authority/websocket");
} else {
    alert('Not support websocket')
}

//连接发生错误的回调方法
websocket.onerror = function(){
    console.log("%c wesocket建立失败!!!", "color:red");
};

//连接成功建立的回调方法
websocket.onopen = function(event){
    console.log("%c wesocket建立成功!!!", "color:red");
    //setMessageInnerHTML("open");
}

//接收到消息的回调方法
websocket.onmessage = function(event){
    setMessageInnerHTML(event.data);
}

//连接关闭的回调方法
websocket.onclose = function(){
    //setMessageInnerHTML("close");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function(){
    websocket.close();
}

//将消息显示在网页上
function setMessageInnerHTML(innerHTML){
    var div = document.createElement("div");
    var divmessage = "<div id=\"mseeage\" style=\"width:300px;background:red;padding:10px 0;position:absolute;right:10px;-webkit-animation:websocketUp .5s 1 ease-in-out forwards;animation:websocketUp .5s 1 ease-in-out forwards\">";
        divmessage+="   <div style=\"wdith:100%;height:30px;border-bottom:1px solid yellow;\">";
        divmessage+="       <span onclick=\"cloaseMessage()\" title=\"关闭\" style=\"float:right;line-height:30px;margin-right:10px;cursor:pointer;\">叉</span>";
        divmessage+="   </div>";
        divmessage+="   <div style=\"text-indent:20px;padding:10px;\">";
        divmessage+="   <div style=\"text-indent:20px;padding:10px;\">";
        divmessage+="   "+innerHTML+"";
        divmessage+="   </div>";
        divmessage+="</div>";
    div.innerHTML = divmessage
    document.body.appendChild(div);
}

//关闭连接
function closeWebSocket(){
    websocket.close();
}

//发送消息
function send(){
    var message = document.getElementById('text').value;
    websocket.send(message);
}

function cloaseMessage() {
    document.getElementById("mseeage").style.display="none";
}