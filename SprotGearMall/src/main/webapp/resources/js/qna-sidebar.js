
 
let chatPopup = null

function openChatPopup(){

 	var url = "/mall/qnaBoard/chat"; 
    var windowName = "chatPopup"; 
    var windowSize = "width=500,height=650"; 
	
	if (chatPopup === null || chatPopup.closed) {
		chatPopup = window.open(url, windowName, windowSize);	
	}else{
	chatPopup.focus();
	}
	
}