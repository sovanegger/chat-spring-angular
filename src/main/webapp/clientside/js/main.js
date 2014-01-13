var APP_NAME = "/chat-spring-angular";
var LOGIN_PAGE_URL = "/login";
var CHAT_MESSAGE_LIMIT = 10;
var chatAppControllers = angular.module('chatAppControllers', []);

var hasStorage = (function() {
	if(typeof(Storage)!=='undefined')
		return true;
	else
		return false;
})();